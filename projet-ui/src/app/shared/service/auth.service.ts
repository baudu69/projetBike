import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public jwt?: string
  public username?: string

  constructor() {
    if (localStorage.getItem('token') && localStorage.getItem('token') != null) {
      this.majToken({username: localStorage.getItem('username')!, jwt: localStorage.getItem('token')!})
    }
  }

  majToken(info: {username: string, jwt: string}) {
    this.jwt = info.jwt
    this.username = info.username
    localStorage.setItem('token', info.jwt)
    localStorage.setItem('username', info.username)
  }
}
