import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomTimelineComponent } from './custom-timeline.component';

describe('CustomTimelineComponent', () => {
  let component: CustomTimelineComponent;
  let fixture: ComponentFixture<CustomTimelineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomTimelineComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CustomTimelineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
