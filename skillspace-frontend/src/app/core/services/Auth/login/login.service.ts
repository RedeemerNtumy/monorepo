import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterTalent } from '../../../shared/model/register-talent';
import { catchError, Observable, of, throwError } from 'rxjs';
import { ILoginUser } from '../../../shared/model/login.interface';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private apiurl = 'https://71c9-41-215-163-167.ngrok-free.app/';

  constructor(private http: HttpClient) {}

  login(user:ILoginUser): Observable<ILoginUser>{
    return this.http.post<ILoginUser>(`${this.apiurl}auth/login`, user)
    .pipe(
      catchError((error: any) => {
        return throwError('Invalid credentials');
      })
    );
  }
}
