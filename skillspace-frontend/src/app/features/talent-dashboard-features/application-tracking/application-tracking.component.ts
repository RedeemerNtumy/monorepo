import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { CustomTimelineComponent } from '../../../core/shared/custom-timeline/custom-timeline.component';


@Component({
  selector: 'app-application-tracking',
  standalone: true,
  imports: [MatIconModule, CustomTimelineComponent],
  templateUrl: './application-tracking.component.html',
  styleUrl: './application-tracking.component.scss'
})
export class ApplicationTrackingComponent {

}
