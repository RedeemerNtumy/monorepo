import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationTalentComponent } from './registration-talent.component';

describe('RegistrationTalentComponent', () => {
  let component: RegistrationTalentComponent;
  let fixture: ComponentFixture<RegistrationTalentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistrationTalentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegistrationTalentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
