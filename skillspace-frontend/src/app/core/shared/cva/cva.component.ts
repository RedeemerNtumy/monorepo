import { CommonModule } from '@angular/common';
import { Component, forwardRef, Input } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-cva',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cva.component.html',
  styleUrl: './cva.component.scss',
  providers:[
    {provide: NG_VALUE_ACCESSOR, multi: true, useExisting: forwardRef(() =>CvaComponent)}
  ]
})
export class CvaComponent implements ControlValueAccessor {
  @Input() name: string ='';
  @Input() type: string='';
  @Input() label?: string;
  @Input() placeholder: string='';
  value:string = '';
  onChange:any = ()=>{};
  onTouched:any = ()=>{};
  writeValue(obj: any): void {
    this.value = obj
  }
  registerOnChange(fn: any): void {
    this.onChange = fn
  }
  registerOnTouched(fn: any): void {
    this.onTouched = fn
  }
  setDisabledState?(isDisabled: boolean): void {
  }

  updateValue(val:string){
    this.onChange(val)
  }
}
