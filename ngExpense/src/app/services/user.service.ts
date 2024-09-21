import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url = environment.baseUrl + "api/users";

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<User[]> {
    return this.http.get<User[]>(this.url).pipe(
      catchError(
        (err: any) => {
          console.log(err);
          return throwError(
            () => new Error('user.service.ts - index() : error retrieving users: ' + err)
          );
        }
      )
    );
  }
}
