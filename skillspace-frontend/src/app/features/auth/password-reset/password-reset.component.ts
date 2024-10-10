import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DynamicInputFieldComponent } from '../../../core/shared/dynamic-input-field/dynamic-input-field.component';
import { LoaderComponent } from '../../../core/shared/loader/loader.component';
import { ToastComponent } from '../../../core/shared/toast/toast.component';
import { ActivatePasswordComponent } from '../activate-password/activate-password.component';
import { ModalService } from '../../../core/services/modal/modal.service';

@Component({
  selector: 'app-password-reset',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    DynamicInputFieldComponent,
    ReactiveFormsModule,
    CommonModule,
    LoaderComponent,
    ToastComponent,
    ActivatePasswordComponent,
  ],
  templateUrl: './password-reset.component.html',
  styleUrl: './password-reset.component.scss',
})
export class PasswordResetComponent implements OnInit {
  forgotPasswordForm!: FormGroup;
  errorMessage: string = '';
  isLoading: boolean = false;
  showToast: boolean = false;
  toastMessage: string = '';
  toastType: string = '';
  isSuccess: boolean = false;
  // showActivePassword: boolean = false;

  forgotConfig = {
    name: 'email',
    type: 'email',
    label: 'Email Address',
    placeholder: 'Enter your email here',
    validators: [Validators.required, Validators.email],
  };

  constructor(
    private fb: FormBuilder,
    private cdr: ChangeDetectorRef,
    private modalService: ModalService
  ) {}

  ngOnInit() {
    this.forgotPasswordForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
    });
  }

  onSubmit() {
    if (this.forgotPasswordForm.valid) {
      console.log(
        'Login details:',
        JSON.stringify(this.forgotPasswordForm.value)
      );
      this.isLoading = true;
      this.cdr.detectChanges();
      setTimeout(() => {
        this.showToast = true;
        this.toastType = 'success';
        this.toastMessage = 'Login successful!';
        this.isLoading = false;
        setTimeout(() => {
          this.modalService.toggleShowActivePassword();
          this.cdr.detectChanges();
        }, 2000);
        this.cdr.detectChanges();
        setTimeout(() => {
          this.forgotPasswordForm.get('email')?.setValue('');
          this.showToast = false;
        }, 2500);
      }, 2000);
    } else {
      this.forgotPasswordForm.markAllAsTouched();
      this.showToast = true;
      this.toastMessage = 'Invalid input, please fill all required fields';
      this.toastType = 'error';
      setTimeout(() => {
        this.showToast = false;
      }, 5000);
    }
  }

  onErrorMessageChange(message: string) {
    if (message) {
      this.showToast = true;
      this.toastType = 'errpr';
      this.toastMessage = message;
    } else {
      this.showToast = false;
    }
  }
}
