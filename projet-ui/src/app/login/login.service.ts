import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<{ jwt: string, username: string }> {
    return this.http.post<{jwt: string, username: string}>('api/auth/signIn', {username, password});
  }
}
