import { Component } from '@angular/core';
import {MatSidenavModule} from '@angular/material/sidenav';
import { HeaderComponent } from '../../features/header/header.component';
import { SidebarComponent } from '../../features/sidebar/sidebar.component';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    MatSidenavModule,
    HeaderComponent,
    SidebarComponent,
    RouterOutlet, 
    FormsModule,
    CommonModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  showFiller = false;
}
