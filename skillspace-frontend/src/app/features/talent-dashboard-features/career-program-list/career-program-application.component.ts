import { Component } from '@angular/core';
import { CvaComponent } from '../../../core/shared/cva/cva.component';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-career-program-application',
  standalone: true,
  imports: [
    CvaComponent,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    RouterLink
  ],
  templateUrl: './career-program-application.component.html',
  styleUrl: './career-program-application.component.scss'
})
export class CareerProgramApplicationComponent {

}
