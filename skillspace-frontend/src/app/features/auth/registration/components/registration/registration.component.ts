import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RegistrationCompanyComponent } from '../registration-company/registration-company.component';
import { RegistrationTalentComponent } from '../registration-talent/registration-talent.component';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [
    CommonModule,
    RegistrationCompanyComponent,
    RegistrationTalentComponent,
  ],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.scss',
})
export class RegistrationComponent {
  isCompanyFormVisible: boolean = true;
  role: 'ADMIN' | 'COMPANY' | 'TALENT' = 'TALENT';
  showOTP = true;

  toggleForm(isCompany: boolean) {
    this.isCompanyFormVisible = isCompany;
  }

  setRole(role: 'ADMIN' | 'COMPANY' | 'TALENT') {
    this.role = role;
    console.log(this.role);
  }

  signupWithGoggle() {}
}
