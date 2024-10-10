import { Component } from '@angular/core';
import { ProgramsHeaderComponent } from "../../../core/shared/programs-header/programs-header.component";
import { ProgramCardComponent } from "../../../core/shared/program-card/program-card.component";
import { CommonModule } from '@angular/common';
import { DraftCardComponent } from "../../../core/shared/draft-card/draft-card.component";

@Component({
  selector: 'app-drafted-programs',
  standalone: true,
  imports: [ProgramsHeaderComponent, CommonModule, DraftCardComponent],
  templateUrl: './drafted-programs.component.html',
  styleUrl: './drafted-programs.component.scss'
})

export class DraftedProgramsComponent {
  drafts=[
    {
      name:'Data Science Internship Program',
      changedDate: 'october 3, 2024'
    },
    {
      name:'Graphic Design Bootcamp Program',
      changedDate: 'october 6, 2024'
    }
  ]
}
