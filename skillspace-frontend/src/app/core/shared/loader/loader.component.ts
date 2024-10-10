import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, EventEmitter, Input, Output } from '@angular/core';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
@Component({
  selector: 'app-loader',
  standalone: true,
  imports: [ProgressSpinnerModule,CommonModule],
  templateUrl: './loader.component.html',
  styleUrl: './loader.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class LoaderComponent {
  @Input() isLoading = false;
  @Input() loadingText = 'Loading...';
  @Input() buttonClass = '';
  @Input() disabled: boolean = false;
  @Output() buttonClick = new EventEmitter<void>();

  onClick() {
    if (!this.isLoading) {
      this.buttonClick.emit();
    }
  }
}
