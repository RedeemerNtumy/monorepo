import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-draft-card',
  standalone: true,
  imports: [],
  templateUrl: './draft-card.component.html',
  styleUrl: './draft-card.component.scss'
})

export class DraftCardComponent {
  @Input() draft!: any
}
