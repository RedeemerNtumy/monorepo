import { Injectable } from '@angular/core';
import { AbstractControl, ValidationErrors, ValidatorFn, FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class FormValidationService {
  getErrorMessage(control: AbstractControl, label: string): string {
    if (control.hasError('required')) {
      return `${label} is required`;
    }
    if (control.hasError('email')) {
      return 'Please enter a valid email address';
    }
    if (control.hasError('minlength')) {
      const requiredLength = control.errors?.['minlength'].requiredLength;
      return `${label} must be at least ${requiredLength} characters long`;
    }
    if (control.hasError('passwordStrength')) {
      return this.getPasswordStrErrorMsg(control.errors?.['passwordStrength']);
    }
    if (control.hasError('passwordMismatch')) {
      return 'Passwords do not match';
    }
    // Add more error checks as needed
    return '';
  }

  private getPasswordStrErrorMsg(strengthError: any): string {
    const missingCriteria = [];
    if (!strengthError.hasMinLength) missingCriteria.push('at least 8 characters');
    if (!strengthError.hasUpperCase) missingCriteria.push('an uppercase letter');
    if (!strengthError.hasLowerCase) missingCriteria.push('a lowercase letter');
    if (!strengthError.hasNumeric) missingCriteria.push('a number');
    if (!strengthError.hasSpecialChar) missingCriteria.push('a special character');

    return `Password must contain ${missingCriteria.join(', ')}`;
  }

  passwordStrCheck(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      if (!value) {
        return null;
      }

      const hasUpperCase = /[A-Z]+/.test(value);
      const hasLowerCase = /[a-z]+/.test(value);
      const hasNumeric = /[0-9]+/.test(value);
      const hasSpecialChar = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/.test(value);
      const hasMinLength = value.length >= 8;

      const passwordValid = hasUpperCase && hasLowerCase && hasNumeric && hasSpecialChar && hasMinLength;

      if (!passwordValid) {
        return {
          passwordStrength: {
            hasUpperCase,
            hasLowerCase,
            hasNumeric,
            hasSpecialChar,
            hasMinLength,
          }
        };
      }

      return null;
    };
  }


}
