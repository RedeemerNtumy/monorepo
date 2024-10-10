import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, inject, Input } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CvaComponent } from '../../../../../core/shared/cva/cva.component';
import { ToastComponent } from '../../../../../core/shared/toast/toast.component';
import { RouterLink } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AuthRegisterService } from '../../../../../core/services/Auth/register/auth-register.service';
import { DataService } from '../../../../../core/services/data/data.service';
import { ActivatePasswordComponent } from '../../../activate-password/activate-password.component';

@Component({
  selector: 'app-registration-company',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    CvaComponent,
    ToastComponent,
    RouterLink,
  ],
  templateUrl: './registration-company.component.html',
  styleUrl: './registration-company.component.scss',
})
export class RegistrationCompanyComponent {
  companyForm!: FormGroup;
  @Input() role: 'ADMIN' | 'COMPANY' | 'TALENT' = 'COMPANY';
  toastMessages: { [key: string]: { message: string; type: string } } = {};
  readonly dialog = inject(MatDialog);

  constructor(
    private fb: FormBuilder,
    private cdr: ChangeDetectorRef,
    private authService: AuthRegisterService,
    private dataService: DataService
  ) {}

  ngOnInit(): void {
    this.companyForm = this.fb.group({
      name: ['', Validators.required],
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
      website: ['', Validators.required],
      certificate: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      contact: ['', Validators.required],
      logo: ['', Validators.required],
    });

    this.companyForm.valueChanges.subscribe(() => {
      this.updateToastMessages();
    });
  }

  private matchPassword(control: AbstractControl) {
    if (!this.companyForm) return null;
    const password = this.companyForm.get('password')?.value;
    return control.value === password ? null : { mismatch: true };
  }

  submitCompanyForm() {
    if (this.companyForm.valid) {
      const formData = new FormData();

      // Append the form values to the FormData object
      formData.append('name', this.companyForm.get('name')?.value);
      formData.append('password', this.companyForm.get('password')?.value);
      formData.append('website', this.companyForm.get('website')?.value);
      formData.append('email', this.companyForm.get('email')?.value);
      formData.append('contact', this.companyForm.get('contact')?.value);

      const certificateFile = this.companyForm.get('certificate')?.value;
      const logoFile = this.companyForm.get('logo')?.value;

      if (certificateFile) {
        formData.append('certificate', certificateFile, certificateFile.name);
      }
      if (logoFile) {
        formData.append('logo', logoFile, logoFile.name);
      }

      this.authService.registerCompany(formData).subscribe({
        next: (response) => {
          console.log('Company registered successfully:', response);
          this.companyForm.reset();
          this.clearToastMessages();
          this.openDialog();
        },
        error: (error) => {
          console.error('Registration error:', error);
          this.toastMessages['general'] = {
            message: 'Registration failed. Please try again.',
            type: 'error',
          };
        },
      });
    } else {
      this.companyForm.markAllAsTouched();
    }
  }

  private updateToastMessages() {
    this.toastMessages = {};

    for (const controlName in this.companyForm.controls) {
      const control = this.companyForm.get(controlName);
      if (control && control.invalid && (control.touched || control.dirty)) {
        if (controlName === 'name') {
          this.toastMessages['name'] = {
            message: 'Company name is required.',
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
        if (controlName === 'password') {
          if (control.errors?.['required']) {
            this.toastMessages['password'] = {
              message: 'Password is required.',
              type: 'error',
            };
          } else if (control.errors?.['minlength']) {
            this.toastMessages['password'] = {
              message: 'Passwords must be at least 8 characters. ',
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
        if (controlName === 'website') {
          this.toastMessages['website'] = {
            message: 'Website is required.',
            type: 'error',
          };
        }
        if (controlName === 'certificate') {
          this.toastMessages['certificate'] = {
            message: 'Certificate is required.',
            type: 'error',
          };
        }
        if (controlName === 'contact') {
          this.toastMessages['contact'] = {
            message: 'Contact information is required.',
            type: 'error',
          };
        }
        if (controlName === 'logo') {
          this.toastMessages['logo'] = {
            message: 'Company logo is required.',
            type: 'error',
          };
        }
      }
    }
  }

  openDialog() {
    const dialogRef = this.dialog.open(ActivatePasswordComponent, {});

    dialogRef.afterClosed().subscribe((result) => {});
  }

  clearToastMessages() {
    this.toastMessages = {};
  }
}
