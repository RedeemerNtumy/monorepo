import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function booleanTrueValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const isValid = control.value === true;
    return isValid ? null : { booleanTrue: { valid: false } };
  };
}