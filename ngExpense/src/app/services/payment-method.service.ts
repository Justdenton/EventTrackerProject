import { PaymentMethod } from './../models/payment-method';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentMethodService {

  url = environment.baseUrl + "api/payment-methods";

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<PaymentMethod[]> {
    return this.http.get<PaymentMethod[]>(this.url).pipe(
      catchError(
        (err: any) => {
          console.log(err);
          return throwError(
            () => new Error('payment-method.service.ts - index() : error retrieving payment methods: ' + err)
          );
        }
      )
    );
  }
}
