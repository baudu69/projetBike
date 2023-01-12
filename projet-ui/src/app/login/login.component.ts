import { Component, OnInit } from '@angular/core';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router, RouterLink} from "@angular/router";
import {LoginService} from "./login.service";
import {AuthService} from "../shared/service/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
    imports: [
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        FormsModule,
        ReactiveFormsModule,
        RouterLink
    ],
  standalone: true
})
export class LoginComponent implements OnInit {

  formLogin: FormGroup = new FormGroup({
    username: new FormControl('userepul', [Validators.required]),
    password: new FormControl('epul', [Validators.required])
  })

  constructor(private loginService: LoginService, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  login() {
    this.loginService.login(this.formLogin.value.username, this.formLogin.value.password).subscribe(
      data => {
        this.authService.majToken(data.username, data.jwt);
        this.authService.isConnected.subscribe(value => {
          if (value) this.router.navigate(['']);
        })
      }
    );
  }

}
