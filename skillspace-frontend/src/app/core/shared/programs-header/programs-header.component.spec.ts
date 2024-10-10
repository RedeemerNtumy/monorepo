import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgramsHeaderComponent } from './programs-header.component';

describe('ProgramsHeaderComponent', () => {
  let component: ProgramsHeaderComponent;
  let fixture: ComponentFixture<ProgramsHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProgramsHeaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProgramsHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
