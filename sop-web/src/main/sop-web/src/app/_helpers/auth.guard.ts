/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    // private authenticationService: AuthenticationService
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    // // const currentUser = this.authenticationService.currentUserValue;
    // if (currentUser) {
    //   if (route.data.roles && route.data.roles.indexOf(currentUser.role) === -1) {
    //     this.router.navigate(['/']);
    //     return false;
    //   }
      return true;
    // }
    this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
  }


}
