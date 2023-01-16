import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {SignupModel} from "./signup.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(
      private http: HttpClient
  ) { }

  signup(signUpModel: SignupModel): Observable<void> {
    return this.http.post<void>('/api/auth/signUp', signUpModel);
  }

}
