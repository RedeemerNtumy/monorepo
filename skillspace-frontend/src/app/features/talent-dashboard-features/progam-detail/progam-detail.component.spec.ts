import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgamDetailComponent } from './progam-detail.component';

describe('ProgamDetailComponent', () => {
  let component: ProgamDetailComponent;
  let fixture: ComponentFixture<ProgamDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProgamDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProgamDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
