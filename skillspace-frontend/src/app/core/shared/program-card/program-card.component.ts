import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-program-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './program-card.component.html',
  styleUrl: './program-card.component.scss'
})

export class ProgramCardComponent {
  @Input() program!: any;
}
