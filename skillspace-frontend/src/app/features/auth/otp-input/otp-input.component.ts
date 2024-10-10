import { CommonModule } from '@angular/common';
import { Component, ElementRef, EventEmitter, Input, Output, QueryList, ViewChildren } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-otp-input',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './otp-input.component.html',
  styleUrl: './otp-input.component.scss'
})

export class OtpInputComponent {
  @Input() otpForm!: FormGroup;
  @Output() otpChanged = new EventEmitter<string>();
  @Input() isInvalid: boolean = false;
  @ViewChildren('otpInput') otpInputs!: QueryList<ElementRef>;

  updateOtpValue(index: number, value: string) {
    const currentOtp = this.otpForm.get('otp')?.value || '';
    const newOtp = currentOtp.substring(0, index) + value + currentOtp.substring(index + 1);
    this.otpForm.get('otp')?.setValue(newOtp.trim());
    this.otpForm.get('otp')?.markAsTouched();
    this.otpChanged.emit(newOtp.trim());
  }

  focusInput(index: number) {
    if (index >= 0 && index < this.otpInputs.length) {
      this.otpInputs.toArray()[index].nativeElement.focus();
    }
  }

  onInput(event: Event, index: number) {
    const input = event.target as HTMLInputElement;
    const value = input.value.slice(-1);

    this.updateOtpValue(index, value);
    input.value = value;

    if (value && index < this.otpInputs.length - 1) {
      this.focusInput(index + 1);
    }
  }

  onKeyDown(event: KeyboardEvent, index: number) {
    if (event.key === 'Backspace') {
      event.preventDefault();
      this.updateOtpValue(index, '');
      if (index > 0) this.focusInput(index - 1);
    } else if (event.key === 'ArrowLeft' && index > 0) {
      event.preventDefault();
      this.focusInput(index - 1);
    } else if (event.key === 'ArrowRight' && index < this.otpInputs.length - 1) {
      event.preventDefault();
      this.focusInput(index + 1);
    }
  }

  onPaste(event: ClipboardEvent) {
    event.preventDefault();
    const pastedData = event.clipboardData?.getData('text');
    if (pastedData) {
      const digits = pastedData.slice(0, 5).replace(/\D/g, '');
      this.otpForm.get('otp')?.setValue(digits);
      digits.split('').forEach((digit, index) => {
        if (index < this.otpInputs.length) {
          this.otpInputs.toArray()[index].nativeElement.value = digit;
        }
      });
      const focusIndex = Math.min(digits.length, this.otpInputs.length - 1);
      this.otpInputs.toArray()[focusIndex].nativeElement.focus();
      this.otpChanged.emit(digits);
    }
  }
}
