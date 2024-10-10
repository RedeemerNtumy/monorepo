import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterTalent } from '../../../shared/model/register-talent';
import { catchError, Observable, of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthRegisterService {
  private apiurl = 'https://13a8-41-215-171-185.ngrok-free.app/';

  constructor(private http: HttpClient) {}

  registerTalent(talentData: RegisterTalent): Observable<RegisterTalent> {
    console.log(talentData);
    return this.http.post<RegisterTalent>(
      `${this.apiurl}api/users/talent/register`,
      talentData
    );
  }

  registerCompany(formData: FormData): Observable<any> {
    return this.http.post<any>(
      `${this.apiurl}api/users/company/register`,
      formData
    );
  }

  verifyOtp(email: string, otp: string): Observable<any> {
    const payload = {
      email: email,
      otp: otp,
    };
    return this.http.post(
      `${this.apiurl}api/users/confirm-otp?email=${email}&otp=${otp}`,
      payload
    );
  }
}
