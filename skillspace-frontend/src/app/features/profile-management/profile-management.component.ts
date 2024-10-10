import { Component } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { PersonalDetailsComponent } from "./personal-details/personal-details.component";
import { EducationComponent } from "./education/education.component";
import { PreferencesComponent } from "./preferences/preferences.component";
import { SecurityComponent } from "./security/security.component";

@Component({
  selector: 'app-profile-management',
  standalone: true,
  imports: [MatTabsModule, PersonalDetailsComponent, EducationComponent, PreferencesComponent, SecurityComponent],
  templateUrl: './profile-management.component.html',
  styleUrl: './profile-management.component.scss',
})
export class ProfileManagementComponent {}
