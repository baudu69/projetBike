import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public jwt?: string
  public username?: string
  public isConnected = new BehaviorSubject<boolean>(false);

  constructor() {
    if (localStorage.getItem('token') && localStorage.getItem('token') != null) {
      this.majToken(localStorage.getItem('username')!, localStorage.getItem('token')!);
    }
  }

  majToken(username?: string, jwt?: string) {
    this.jwt = jwt;
    this.username = username;

    if (jwt) localStorage.setItem('token', jwt);
    else localStorage.removeItem('token');

    if (username) localStorage.setItem('username', username);
    else localStorage.removeItem('username');

    this.isConnected.next(this.jwt !== undefined);
  }

  logout() {
    this.majToken(undefined, undefined);
  }

}
