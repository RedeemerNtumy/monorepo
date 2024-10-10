import { Component } from '@angular/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {provideNativeDateAdapter} from '@angular/material/core';
import { CustomCheckBoxComponent } from '../../../core/shared/custom-check-box/custom-check-box.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { booleanTrueValidator } from '../../../core/shared/custom-form-validators/boolean.validator';

@Component({
  selector: 'app-apply-to-program',
  standalone: true,
  imports: [
    MatDatepickerModule,
    MatInputModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    CustomCheckBoxComponent
  ],
  providers: [provideNativeDateAdapter()],
  templateUrl: './apply-to-program.component.html',
  styleUrl: './apply-to-program.component.scss'
})
export class ApplyToProgramComponent {
  minDate: Date;
  applicationForm = new FormGroup({
    interest: new FormControl('', [Validators.required]),
    confirmProfileInfo: new FormControl(false, [booleanTrueValidator()]),
    confirmTerms: new FormControl(false, [booleanTrueValidator()]),
    preferedStartDate: new FormControl('',[Validators.required]),
  });
  constructor(){
    const currentYear = new Date().getFullYear();
    this.minDate = new Date(currentYear - 20, 0, 1);
  }
  submit() {
    if (this.applicationForm.valid) {
      console.log(this.applicationForm.valid)
      console.log(this.applicationForm.value)
    }
  }
}
