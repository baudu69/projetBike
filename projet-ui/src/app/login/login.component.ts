import { Component, OnInit } from '@angular/core';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
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
    ReactiveFormsModule
  ],
  standalone: true
})
export class LoginComponent implements OnInit {

  formLogin: FormGroup = new FormGroup({
    username: new FormControl('userepul', [Validators.required]),
    password: new FormControl('epul', [Validators.required])
  })

  constructor(private loginService: LoginService, private authService: AuthService) { }

  ngOnInit(): void {
  }

  login() {
    this.loginService.login(this.formLogin.value.username, this.formLogin.value.password).subscribe(
      data => {
        this.authService.majToken(data)
      }
    );
  }

}
