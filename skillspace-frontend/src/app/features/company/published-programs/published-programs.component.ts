import { Component } from '@angular/core';
import { MatDividerModule } from '@angular/material/divider';
import { ProgramCardComponent } from "../../../core/shared/program-card/program-card.component";
import { CommonModule } from '@angular/common';
import { ProgramsHeaderComponent } from "../../../core/shared/programs-header/programs-header.component";

@Component({
  selector: 'app-published-programs',
  standalone: true,
  imports: [MatDividerModule, ProgramCardComponent, CommonModule, ProgramsHeaderComponent],
  templateUrl: './published-programs.component.html',
  styleUrl: './published-programs.component.scss'
})

export class PublishedProgramsComponent {
  publishedPrograms = [
    {
      name: 'Data Science Internship Program',
      publishedDate: 'May 15, 2024',
      applications: 45,
      status: 'Active'
    },
    {
      name: 'Frontend Developer Bootcamp',
      publishedDate: 'June 1, 2024',
      applications: 60,
      status: 'Closed'
    },
    {
      name: 'Full Stack Web Development Course',
      publishedDate: 'April 10, 2024',
      applications: 120,
      status: 'Active'
    }
  ];
}
