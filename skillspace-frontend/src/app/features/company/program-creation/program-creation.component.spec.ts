import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgramCreationComponent } from './program-creation.component';

describe('ProgramCreationComponent', () => {
  let component: ProgramCreationComponent;
  let fixture: ComponentFixture<ProgramCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProgramCreationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProgramCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
