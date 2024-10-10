import { CommonModule } from '@angular/common';
import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import {
  FormsModule,
  Validators,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
} from '@angular/forms';
import { InputOtpModule } from 'primeng/inputotp';
import { ToastComponent } from '../../../core/shared/toast/toast.component';
import { OtpInputComponent } from '../otp-input/otp-input.component';
import { Router } from '@angular/router';
import { AuthRegisterService } from '../../../core/services/Auth/register/auth-register.service';
import { take } from 'rxjs';
import { RegisterTalent } from '../../../core/shared/model/register-talent';
import { DataService } from '../../../core/services/data/data.service';

@Component({
  selector: 'app-verification',
  standalone: true,
  imports: [
    InputOtpModule,
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    ToastComponent,
    OtpInputComponent,
  ],
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.scss'],
})
export class VerificationComponent implements OnInit, OnDestroy {
  email: string | undefined = undefined;
  @Input() actionType!: 'register' | 'reset';

  userData: RegisterTalent | null = null;
  otpForm: FormGroup;
  countdownMinutes: number = 9;
  countdownSeconds: number = 59;
  private countdownInterval: any;
  showToast: boolean = false;
  toastMessage: string = '';
  toastType: string = '';
  isSuccess: boolean = false;
  isExpired: boolean = false;
  isOtpInvalid: boolean = false;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthRegisterService,
    private dataService: DataService
  ) {
    this.otpForm = this.fb.group({
      otp: ['', [Validators.required, Validators.maxLength(5)]],
    });
  }

  ngOnInit() {
    this.startCountdown();
    console.log(this.dataService.userData$.getValue());
    // .subscribe((data) => {
    //   this.userData = data;
    //   if (this.userData && this.userData.email) {
    //     this.email = this.userData.email;
    //   }
    // });
    this.email = this.dataService.userData$.getValue()?.email;
    console.log(this.email);
  }

  ngOnDestroy() {
    this.stopCountdown();
  }

  private startCountdown() {
    this.countdownInterval = setInterval(() => {
      if (this.countdownSeconds > 0) {
        this.countdownSeconds--;
      } else if (this.countdownMinutes > 0) {
        this.countdownMinutes--;
        this.countdownSeconds = 59;
      } else {
        this.stopCountdown();
        this.handleOtpExpiry();
      }
    }, 1000);
  }

  private stopCountdown() {
    if (this.countdownInterval) {
      clearInterval(this.countdownInterval);
    }
  }

  private handleOtpExpiry() {
    this.isExpired = true;
  }

  formatCountdown(): string {
    if (this.isExpired) {
      return 'Request for new code';
    }
    return `${this.countdownMinutes}:${this.countdownSeconds < 10 ? '0' : ''}${
      this.countdownSeconds
    }`;
  }

  onOtpChanged(otp: string) {
    this.otpForm.get('otp')?.setValue(otp);
    this.isOtpInvalid = false;
  }

  toastTimeOut() {
    setTimeout(() => {
      this.showToast = false;
    }, 5000);
  }

  verifyOtp() {
    if (this.otpForm.valid && !this.isExpired) {
      const otp = this.otpForm.get('otp')?.value;

      if (otp) {
        this.authService
          .verifyOtp(this.email!, otp)
          .pipe(take(1))
          .subscribe({
            next: (response) => {
              console.log(response);
              if (response.message) {
                this.showToast = true;
                this.toastMessage = 'OTP verified successfully!';
                this.toastType = 'success';
                this.isSuccess = true;
                setTimeout(() => {
                  this.router.navigate(['/auth/login']);
                }, 2000);
              } else {
                this.showToast = true;
                this.toastMessage = 'Invalid OTP. Please try again.';
                this.toastType = 'error';
                this.isOtpInvalid = true;
                this.toastTimeOut();
              }
            },
            error: (error) => {
              console.error('Verification error:', error);
              this.showToast = true;
              this.toastMessage = 'An error occurred. Please try again.';
              this.toastType = 'error';
              this.toastTimeOut();
            },
          });
      } else {
        console.error('Email not found. Verification failed.');
        this.showToast = true;
        this.toastMessage = 'Email not found. Please try again.';
        this.toastType = 'error';
        this.toastTimeOut();
      }
    } else if (!this.isExpired) {
      this.showToast = true;
      this.toastMessage = 'Please enter a valid 5-digit OTP.';
      this.toastType = 'error';
      this.isOtpInvalid = true;
      this.toastTimeOut();
    }
  }

  requestNewCode() {
    this.isExpired = false;
    this.countdownMinutes = 9;
    this.countdownSeconds = 59;
    this.showToast = false;
    this.isOtpInvalid = false;
    this.startCountdown();
  }
}
