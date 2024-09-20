import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Expense } from '../models/expense';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {

  url = environment.baseUrl + "api/expenses";

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Expense[]> {
    return this.http.get<Expense[]>(this.url).pipe(
      catchError(
        (err: any) => {
          console.log(err);
          return throwError(
            () => new Error('expense.service.ts - index() : error retrieving expense: ' + err)
          );
        }
      )
    );
  }

  // create(expense: Expense): Observable<Expense> {
  //   return this.http.post<Expense>(this.url, expense).pipe(
  //     catchError(
  //       (err: any) => {
  //         console.log(err);
  //         return throwError(
  //           () => new Error('expense.service.ts - create() : error creating expense: ' + err)
  //         );
  //       }
  //     )
  //   );
  // }


}
