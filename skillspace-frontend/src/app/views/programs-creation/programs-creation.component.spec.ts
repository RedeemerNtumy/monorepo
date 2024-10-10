import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgramsCreationComponent } from './programs-creation.component';

describe('ProgramsCreationComponent', () => {
  let component: ProgramsCreationComponent;
  let fixture: ComponentFixture<ProgramsCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProgramsCreationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProgramsCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
