import { Category } from '../models/category';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  url = environment.baseUrl + "api/categories";

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Category[]> {
    return this.http.get<Category[]>(this.url).pipe(
      catchError(
        (err: any) => {
          console.log(err);
          return throwError(
            () => new Error('category.service.ts - index() : error retrieving categories: ' + err)
          );
        }
      )
    );
  }
}
