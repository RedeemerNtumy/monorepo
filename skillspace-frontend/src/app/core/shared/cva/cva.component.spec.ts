import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CvaComponent } from './cva.component';

describe('CvaComponent', () => {
  let component: CvaComponent;
  let fixture: ComponentFixture<CvaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CvaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CvaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
