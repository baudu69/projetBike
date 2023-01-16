import {Routes} from "@angular/router";
import {AccueilComponent} from "./accueil/accueil.component";
import {LogoutComponent} from "./logout/logout.component";
import {AuthGuard} from "./shared/guard/auth.guard";
import {SortieListComponent} from "./sortie-list/sortie-list.component";
import {SortieComponent} from "./sortie/sortie.component";
import {ProfileComponent} from "./profile/profile.component";

export const routes: Routes = [
  {path: '', component: AccueilComponent, canActivate: [AuthGuard]},
  {path: 'login', loadComponent: () => import('./login/login.component').then(m => m.LoginComponent)},
  {path: 'logout', component: LogoutComponent},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
  {path: 'signup', loadComponent: () => import('./signup/signup.component').then(m => m.SignupComponent)},
  {path: 'sortie', component: SortieListComponent, canActivate: [AuthGuard]},
  {path: 'sortie/:sortieId', component: SortieComponent, canActivate: [AuthGuard]},
]
