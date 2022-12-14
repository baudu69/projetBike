import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthService} from "../service/auth.service";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private authService : AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (this.authService.jwt) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.authService.jwt}`
        }
      });
    }
    return next.handle(request);
  }
}
