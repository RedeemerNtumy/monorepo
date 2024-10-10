import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { DynamicInputFieldComponent } from "../../../core/shared/dynamic-input-field/dynamic-input-field.component";
import { AbstractControl, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastComponent } from "../../../core/shared/toast/toast.component";
import { LoaderComponent } from "../../../core/shared/loader/loader.component";
import { FormValidationService } from '../../../core/services/form-validation/form-validation.service';

@Component({
  selector: 'app-new-password',
  standalone: true,
  imports: [
    DynamicInputFieldComponent,
    ReactiveFormsModule, FormsModule,
    CommonModule,
    ToastComponent,
    LoaderComponent
],
  templateUrl: './new-password.component.html',
  styleUrl: './new-password.component.scss'
})

export class NewPasswordComponent implements OnInit{
  passwordForm!: FormGroup;
  showToast = false;
  isLoading = false;
  toastMessage: string = '';
  toastType: string = 'success';
  isSuccess: boolean = false;

  passwordConfig=[
    {
      label: 'New Password',
      type: 'password',
      name: 'password',
      placeholder: '******',
      validators: [Validators.required, Validators.minLength(8)]
    },
    {
      label: 'Confirm Password',
      type: 'password',
      name: 'confirmPassword',
      placeholder: '******',
      validators: [Validators.required, Validators.minLength(8)]
    }
   ,
  ]


  constructor(private fb:FormBuilder,
    private cdr: ChangeDetectorRef,
    private validationService: FormValidationService
  ) {
  }

  ngOnInit(){
    this.passwordForm = this.fb.group({});
    this.passwordForm.addControl('password',
      this.fb.control ('',[Validators.required, Validators.minLength(8)])
    )
    this.passwordForm.addControl('confirmPassword',
      this.fb.control ('',[Validators.required, ])
    )
  }

  

  onErrorMessageChange(message: string) {
    if (message) {
      this.showToast = true;
      this.toastType = 'warn';
      this.toastMessage = message;
    } else {
      this.showToast = false;
    }
  }

  toastTimeout() {
    setTimeout(() => {
      this.showToast = false;
    }, 5000);
  }



  create(){
    if(this.passwordForm.valid){
      console.log('Login details:', JSON.stringify(this.passwordForm.value));
      this.isLoading = true;
      this.cdr.detectChanges();
      setTimeout(() => {
        this.showToast = true;
        this.toastType = 'success';
        this.toastMessage = 'Login successful!';
        this.isLoading = false;
        this.isSuccess = true;
        this.cdr.detectChanges();
        this.toastTimeout();
      }, 2000);
    } else {

      this.toastMessage = 'Enter a valid password and try again';
      this.toastType = 'error';
      this.showToast = true;
      this.toastTimeout();
    }

  }
}
