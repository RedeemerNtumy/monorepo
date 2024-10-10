import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplyToProgramComponent } from './apply-to-program.component';

describe('ApplyToProgramComponent', () => {
  let component: ApplyToProgramComponent;
  let fixture: ComponentFixture<ApplyToProgramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApplyToProgramComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ApplyToProgramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
