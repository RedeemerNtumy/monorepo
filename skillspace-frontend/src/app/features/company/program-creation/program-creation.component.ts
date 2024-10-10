import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CvaComponent } from "../../../core/shared/cva/cva.component";
import { TextareaCvaComponent } from "../../../core/shared/textarea-cva/textarea-cva.component";
import { LoaderComponent } from "../../../core/shared/loader/loader.component";

@Component({
  selector: 'app-program-creation',
  standalone: true,
  imports: [CvaComponent, TextareaCvaComponent, LoaderComponent,FormsModule,ReactiveFormsModule],
  templateUrl: './program-creation.component.html',
  styleUrl: './program-creation.component.scss'
})
export class ProgramCreationComponent {
  isLoading = false;
  createProgram!: FormGroup;

  constructor(private fb: FormBuilder) {
    this.createProgram = this.fb.group({
      programName: ['', Validators.required],
      programDescription: ['', Validators.required],
      programRequirements: ['', Validators.required],
      requireEarnedBadges: ['', Validators.required],
      optionalBadges: [''],
      programPicture: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required]
    });
  }

  ngOnInit() {
    // Initially disable the "Publish" button
    this.createProgram.statusChanges.subscribe((status) => {
      const publishButton = document.getElementById('btn-publish') as HTMLButtonElement;
      if (status === 'VALID') {
        publishButton.disabled = false;
      } else {
        publishButton.disabled = true;
      }
    });
  }
}
