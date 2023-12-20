import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../common/user';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api/1.0/user';

  constructor(private httpClient: HttpClient) { }

  getUserList(): Observable<User[]> {
    return this.httpClient
      .get<GetResponseUser>(this.baseUrl)
      .pipe(map((response) => response._embedded.user));
  }

}

interface GetResponseUser {
  _embedded: {
    user: User[];
  };
}