import { Component, ChangeDetectionStrategy } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CvaComponent } from '../../../core/shared/cva/cva.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';
import { CustomCheckBoxComponent } from "../../../core/shared/custom-check-box/custom-check-box.component";

@Component({
  selector: 'app-personal-details',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CvaComponent,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    CommonModule,
    CustomCheckBoxComponent
],
  providers: [provideNativeDateAdapter()],
  templateUrl: './personal-details.component.html',
  styleUrl: './personal-details.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PersonalDetailsComponent {
  PersonalDetails!: FormGroup;

  constructor(private fb: FormBuilder) {
    this.createForm();
  }

  createForm() {
    this.PersonalDetails = this.fb.group({
      profilePicture: [null],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      introduction: ['', [Validators.required, Validators.maxLength(250)]],
      cv: [null],
      dob: [null, Validators.required],
      nationality: ['', Validators.required],
      location: ['', Validators.required],
      phone: ['', Validators.required],
      phonePublic: [false],
      socialMedia: this.fb.array([this.fb.control('')]),
      portfolioLinks: this.fb.array([this.fb.control('')]),
    });
  }

  // Getters for form arrays
  get socialMedia(): FormArray {
    return this.PersonalDetails.get('socialMedia') as FormArray;
  }

  get portfolioLinks(): FormArray {
    return this.PersonalDetails.get('portfolioLinks') as FormArray;
  }

  // Methods to add/remove controls
  addSocialMedia() {
    this.socialMedia.push(this.fb.control(''));
  }

  removeSocialMedia(index: number) {
    this.socialMedia.removeAt(index);
  }

  addPortfolioLink() {
    this.portfolioLinks.push(this.fb.control(''));
  }

  removePortfolioLink(index: number) {
    this.portfolioLinks.removeAt(index);
  }

  // Form submission
  onSubmit() {
    if (this.PersonalDetails.valid) {
      console.log(this.PersonalDetails.value);
    } else {
      console.error('Form is invalid');
    }
  }

  // Cancel changes
  cancel() {
    this.PersonalDetails.reset();
  }
}
