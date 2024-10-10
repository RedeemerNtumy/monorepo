import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ModalService {
  showActivePasswordSubject = new BehaviorSubject<boolean>(false);
   showActivePassword$ = this.showActivePasswordSubject.asObservable();

  constructor() { }

  toggleShowActivePassword(){
    this.showActivePasswordSubject.next(!this.showActivePasswordSubject.value);
  }
}
