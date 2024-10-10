import { Component, Input, forwardRef } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR, FormControl, ReactiveFormsModule, FormsModule } from '@angular/forms';

@Component({
  selector: 'app-textarea-cva',
  templateUrl: './textarea-cva.component.html',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => TextareaCvaComponent),
      multi: true
    }
  ]
})
export class TextareaCvaComponent implements ControlValueAccessor {
  @Input() label: string='Textarea';
  @Input() placeholder: string = 'textarea';
  @Input() rows: number = 3;
  @Input() formControlName!: string;

  value = '';

  onChange = (value: string) => {};
  onTouched = () => {};

  writeValue(value: string): void {
    this.value = value;
    this.onChange(this.value);
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  setDisabledState?(isDisabled: boolean): void {
    // handle disabled state if necessary
  }
}
