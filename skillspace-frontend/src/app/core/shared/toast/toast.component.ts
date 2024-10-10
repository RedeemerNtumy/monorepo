import { Component, Input, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-toast',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './toast.component.html',
  styleUrl: './toast.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class ToastComponent  {
  @Input() message: string = '';
  @Input() toastType: string = 'success';
  @Input() duration: number = 5000;

  getIconPath(): string {
    const basePath = '../../../../assets/images/';
    switch (this.toastType) {
      case 'success':
        return `${basePath}success.svg`;
      case 'error':
        return `${basePath}error.svg`;
      case 'warn':
        return `${basePath}warn.svg`;
      default:
        return `${basePath}warn.svg`;
    }
  }

  getTitle(): string {
    switch(this.toastType){
      case 'success':
        return 'Success';
      case 'error':
        return 'Error';
      case 'warn':
        return 'Password Requirement';
      default:
        return 'Warning';
    }
  }
}
