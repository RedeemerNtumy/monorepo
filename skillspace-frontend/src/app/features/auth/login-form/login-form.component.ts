import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component,} from '@angular/core';
import { ReactiveFormsModule, FormGroup, Validators, FormBuilder} from '@angular/forms';
import { MatDividerModule } from '@angular/material/divider';
import { RouterModule } from '@angular/router';
import { DynamicInputFieldComponent } from '../../../core/shared/dynamic-input-field/dynamic-input-field.component';
import { LoaderComponent } from '../../../core/shared/loader/loader.component';
import { ToastComponent } from '../../../core/shared/toast/toast.component';
import { LoginService } from '../../../core/services/Auth/login/login.service';
import { CvaComponent } from '../../../core/shared/cva/cva.component';


@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [
    MatDividerModule,
    DynamicInputFieldComponent,
    ReactiveFormsModule,
    CommonModule,
    ToastComponent,
    LoaderComponent,
    RouterModule,
    CvaComponent
  ],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoginFormComponent {
  loginForm!: FormGroup;
  showToast = false;
  isLoading = false;
  toastMessage: string = '';
  toastType: string = 'success';



  constructor(private fb: FormBuilder, private cdr: ChangeDetectorRef,
    private authLoginService: LoginService
  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({});
    this.loginForm.addControl(
      'email',
      this.fb.control('', [Validators.required, Validators.email])
    );
    this.loginForm.addControl(
      'password',
      this.fb.control('', [Validators.required])
    );
  }

  toastTimeOut() {
    setTimeout(() => {
      this.showToast = false;
    }, 5000);
  }

  login() {
    if (this.loginForm.valid) {
      this.isLoading = true;
      console.log('Login details:', JSON.stringify(this.loginForm.value));
      const {email, password} = this.loginForm.value;
      this.authLoginService.login({email, password}).subscribe(
        {
          next: (res) => {
            this.isLoading = false;
            console.log(res);
            this.showToast = true;
            this.toastTimeOut();
            this.toastMessage = 'Login successful';
          },
          error: (error) => {
            this.isLoading = false;
            console.log(error);
            this.cdr.detectChanges();
            this.toastMessage = error.message;
            this.toastType = 'error';
            this.showToast = true;
            this.toastTimeOut();
          }
        }
      )
    } else {
      this.isLoading = false;
      this.toastMessage = 'Invalid input(s), please all required fields';
      this.toastType = 'error';
      this.showToast = true;
      this.toastTimeOut();
    }
  }

  onErrorMessageChange(message: string) {
    if (message) {
      this.showToast = true;
      this.toastType = 'error';
      this.toastMessage = message;
    } else {
      this.showToast = false;
    }
  }
}
