import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CareerProgramApplicationComponent } from './career-program-application.component';

describe('CareerProgramApplicationComponent', () => {
  let component: CareerProgramApplicationComponent;
  let fixture: ComponentFixture<CareerProgramApplicationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CareerProgramApplicationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CareerProgramApplicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
