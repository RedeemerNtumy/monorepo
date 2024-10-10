import { Component, forwardRef, Input } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-custom-check-box',
  standalone: true,
  imports: [],
  providers:[
    {provide: NG_VALUE_ACCESSOR, useExisting: forwardRef(()=>CustomCheckBoxComponent), multi: true}
  ],
  templateUrl: './custom-check-box.component.html',
  styleUrl: './custom-check-box.component.scss'
})
export class CustomCheckBoxComponent implements ControlValueAccessor{
  @Input() text:string = "";
  value:boolean = false;
  onChange:any = ()=>{};
  onTouch:any = ()=>{};
  writeValue(obj: any): void {
    this.value = obj;
  }
  registerOnChange(fn: any): void {
    this.onChange = fn;
  }
  registerOnTouched(fn: any): void {
    this.onTouch = fn;
  }
  setDisabledState?(isDisabled: boolean): void {
    // throw new Error('Method not implemented.');
  }

  toggleCheck(){
    this.value = !this.value;
    this.onChange(this.value);
  }
}
