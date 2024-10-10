import { CommonModule } from '@angular/common';
import {
  ChangeDetectorRef,
  Component,
  Input,
  inject,
  ChangeDetectionStrategy,
} from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthRegisterService } from '../../../../../core/services/Auth/register/auth-register.service';
import { CvaComponent } from '../../../../../core/shared/cva/cva.component';
import { RegisterTalent } from '../../../../../core/shared/model/register-talent';
import { ToastComponent } from '../../../../../core/shared/toast/toast.component';
import { VerificationComponent } from '../../../verification/verification.component';
import { RouterLink } from '@angular/router';
import { ActivatePasswordComponent } from '../../../activate-password/activate-password.component';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { DataService } from '../../../../../core/services/data/data.service';

@Component({
  selector: 'app-registration-talent',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    ToastComponent,
    CvaComponent,
    VerificationComponent,
    RouterLink,
    ActivatePasswordComponent,
    MatButtonModule,
    MatDialogModule,
  ],
  templateUrl: './registration-talent.component.html',
  styleUrls: ['./registration-talent.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class RegistrationTalentComponent {
  talentForm!: FormGroup;
  @Input() role: 'ADMIN' | 'COMPANY' | 'TALENT' = 'TALENT';
  showOTP: boolean = false;
  toastMessages: { [key: string]: { message: string; type: string } } = {};
  readonly dialog = inject(MatDialog);

  constructor(
    private fb: FormBuilder,
    private cdr: ChangeDetectorRef,
    private authService: AuthRegisterService,
    private dataService: DataService
  ) {}

  openDialog() {
    const dialogRef = this.dialog.open(ActivatePasswordComponent, {});

    dialogRef.afterClosed().subscribe((result) => {});
  }

  ngOnInit(): void {
    this.talentForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.email, Validators.required]],
      contact: ['', Validators.required],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.pattern(
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&_])[A-Za-z\d@$!%*?&_]+$/
          ),
        ],
      ],
      confirmPassword: [
        '',
        [Validators.required, this.matchPassword.bind(this)],
      ],
    });

    this.talentForm.valueChanges.subscribe(() => {
      this.updateToastMessages();
    });
  }

  private matchPassword(control: AbstractControl) {
    if (!this.talentForm) return null;
    const password = this.talentForm.get('password')?.value;
    return control.value === password ? null : { mismatch: true };
  }

  submitTalentForm() {
    if (this.talentForm.valid) {
      const { name, email, contact, password } = this.talentForm.value;

      console.log('Form is valid. Submitting...');

      const talentData: RegisterTalent = {
        name: name,
        email: email,
        contact: contact,
        password: password,
      };
      this.dataService.setUserData(talentData);
      this.authService.registerTalent(talentData).subscribe((val) => {
        this.dataService.setUserData(talentData);
        console.log(val);
      });

      this.talentForm.reset();
      this.openDialog();
      console.log(talentData);
      this.clearToastMessages();
    } else {
      console.log('Form is invalid. Cannot submit.');
      this.talentForm.markAllAsTouched();
    }
  }

  private updateToastMessages() {
    this.toastMessages = {};

    for (const controlName in this.talentForm.controls) {
      const control = this.talentForm.get(controlName);
      if (control && control.invalid && (control.touched || control.dirty)) {
        if (controlName === 'name') {
          this.toastMessages['name'] = {
            message: 'Name is required.',
            type: 'error',
          };
        }
        if (controlName === 'email') {
          if (control.errors?.['required']) {
            this.toastMessages['email'] = {
              message: 'Email is required.',
              type: 'error',
            };
          } else if (control.errors?.['email']) {
            this.toastMessages['email'] = {
              message: 'Invalid email format.',
              type: 'error',
            };
          }
        }
        if (controlName === 'contact') {
          this.toastMessages['contact'] = {
            message: 'Contact is required.',
            type: 'error',
          };
        }
        if (controlName === 'password') {
          if (control.errors?.['required']) {
            this.toastMessages['password'] = {
              message: 'Password is required.',
              type: 'error',
            };
          } else if (control.errors?.['minlength']) {
            this.toastMessages['password'] = {
              message: 'Passwords must be at least 8 characters.',
              type: 'error',
            };
          } else if (control.errors?.['pattern']) {
            this.toastMessages['password'] = {
              message:
                'Passwords must be at least 8 characters with a mix of upper case, lower case, and special characters.',
              type: 'warn',
            };
          }
        }
        if (controlName === 'confirmPassword') {
          if (control.errors?.['mismatch']) {
            this.toastMessages['confirmPassword'] = {
              message: 'Passwords do not match.',
              type: 'error',
            };
          }
        }
      }
    }
  }

  clearToastMessages() {
    this.toastMessages = {};
  }
}
