import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IUser} from "../model/iuser";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
      private http: HttpClient,
  ) { }

  getUser(): Observable<IUser> {
    return this.http.get<IUser>('/api/utilisateur');
  }

}
