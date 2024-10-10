import { Component, ChangeDetectionStrategy } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import {
  MatDialog,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';

@Component({
  selector: 'app-activate-password',
  standalone: true,
  imports: [RouterModule, MatButtonModule, MatDialogModule],
  templateUrl: './activate-password.component.html',
  styleUrl: './activate-password.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ActivatePasswordComponent {
  constructor(
    private dialogRef: MatDialogRef<ActivatePasswordComponent>,
    private router: Router
  ) {}

  continueToOtp() {
    this.dialogRef.close();
    this.router.navigate(['/auth/verification']);
  }

  openMailApp() {
    this.dialogRef.close();
    window.location.href = 'https://mail.google.com';
  }
}
