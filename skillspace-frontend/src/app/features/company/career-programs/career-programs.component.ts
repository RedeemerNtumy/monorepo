import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-career-programs',
  standalone: true,
  imports: [RouterModule,CommonModule,MatDividerModule,RouterModule],
  templateUrl: './career-programs.component.html',
  styleUrls: ['./career-programs.component.scss']
})
export class CareerProgramsComponent {
  activeTab: string = 'create';

  setActiveTab(tab: string) {
    this.activeTab = tab;
  }
}
