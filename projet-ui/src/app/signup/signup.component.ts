import {Component, OnInit} from '@angular/core';
import {SignupService} from "./signup.service";
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators
} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from '@angular/material/core';
import {MatButtonModule} from "@angular/material/button";
import {SignupModel} from "./signup.model";
import {MatDialog} from "@angular/material/dialog";
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  imports: [
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    MatSnackBarModule
  ],
  standalone: true
})
export class SignupComponent implements OnInit {

  formSignUp: FormGroup = new FormGroup<any>({
    lastname: new FormControl<string>('', [Validators.required]),
    firstname: new FormControl<string>('', [Validators.required]),
    username: new FormControl<string>('', [Validators.required]),
    password: new FormControl<string>('', [Validators.required]),
    passwordConfirm: new FormControl<string>('', [Validators.required, this.passwordValidator()]),
    poids: new FormControl<number>(0.0, [Validators.required]),
    taille: new FormControl<number>(0.0, [Validators.required]),
    birthdate: new FormControl<Date>(new Date(), [Validators.required]),
  });

  constructor(private signUpService: SignupService, private matSnackBar: MatSnackBar, private router: Router) {
  }

  ngOnInit(): void {
  }

  private passwordValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (!this.formSignUp)
        return null
      const incorrect = control.value !== this.formSignUp.controls['password'].value;
      return incorrect ? {'Mot de passe incorrect': {value: control.value}} : null;
    };
  }


  valid() {
    console.log(this.formSignUp.value);
    const formSignupModel: SignupModel = {
      lastname: this.formSignUp.controls['lastname'].value,
      firstname: this.formSignUp.controls['firstname'].value,
      username: this.formSignUp.controls['username'].value,
      password: this.formSignUp.controls['password'].value,
      poids: this.formSignUp.controls['poids'].value,
      taille: this.formSignUp.controls['taille'].value,
      birthdate: this.formSignUp.controls['birthdate'].value,
    }
    this.signUpService.signup(formSignupModel).subscribe({
      next: (response) => {
        this.matSnackBar.open("Inscription réussie", "Fermer", {duration: 2000});
        this.router.navigate(['/login']);
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
        this.matSnackBar.open("Erreur lors de l'inscription", "Fermer", {duration: 2000});
      },
    });
  }
}
