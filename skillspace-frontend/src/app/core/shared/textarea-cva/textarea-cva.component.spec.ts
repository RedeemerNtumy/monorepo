import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TextareaCvaComponent } from './textarea-cva.component';

describe('TextareaCvaComponent', () => {
  let component: TextareaCvaComponent;
  let fixture: ComponentFixture<TextareaCvaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TextareaCvaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TextareaCvaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
