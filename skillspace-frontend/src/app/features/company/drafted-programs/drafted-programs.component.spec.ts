import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DraftedProgramsComponent } from './drafted-programs.component';

describe('DraftedProgramsComponent', () => {
  let component: DraftedProgramsComponent;
  let fixture: ComponentFixture<DraftedProgramsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DraftedProgramsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DraftedProgramsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
