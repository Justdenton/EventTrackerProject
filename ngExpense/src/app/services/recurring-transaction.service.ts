import { RecurringTransaction } from '../models/recurring-transaction';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecurringTransactionService {

  url = environment.baseUrl + "api/categories";

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<RecurringTransaction[]> {
    return this.http.get<RecurringTransaction[]>(this.url).pipe(
      catchError(
        (err: any) => {
          console.log(err);
          return throwError(
            () => new Error('recurring-transaction.service.ts - index() : error retrieving recurring transactions: ' + err)
          );
        }
      )
    );
  }
}
