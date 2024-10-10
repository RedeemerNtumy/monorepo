import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-programs-header',
  standalone: true,
  imports: [],
  templateUrl: './programs-header.component.html',
  styleUrl: './programs-header.component.scss'
})

export class ProgramsHeaderComponent {
  @Input() title!: string;
  @Input() subtitle!: string;
}
