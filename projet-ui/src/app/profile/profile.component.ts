import {Component, OnInit} from '@angular/core';
import {UserService} from "../shared/service/user.service";
import {IUser} from "../shared/model/iuser";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user?: IUser;

  constructor(
      private userService: UserService,
  ) { }

  ngOnInit(): void {
    this.userService.getUser().subscribe({
      next: (user: IUser) => {
        this.user = user;
      },
      error: (error: HttpErrorResponse) => {
        alert("Une erreur est survenue : " + error.status + " " + error.statusText);
      },
    });
  }

}
