import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, retryWhen, scan, throwError } from 'rxjs';
import { LocalstorageService } from '../../localStorage/localstorage.service';

export const httpInterceptor: HttpInterceptorFn = (req, next) => {
  let LStorage = inject(LocalstorageService)
  return next(req.clone({
    headers: req.headers.append('X-Authentication-Token', LStorage.getItem('token')),
  })).pipe(
    retryWhen(errors => 
      errors.pipe(
        scan((retryCount, error) => {
          retryCount += 1;
          if (retryCount >= 3) {
            throw error;
          }
          return retryCount;
        }, 0)
      )
    ),
    catchError(err => {
      return throwError(() => new Error('All retries failed:'))
    })
  );
};
