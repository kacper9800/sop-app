import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng';
import {TabMenuModule} from 'primeng/tabmenu';


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  items: MenuItem[];

  activeItem: MenuItem;
  logged: boolean;
  aboutDialog = false;

  ngOnInit() {
    this.logged = false;
    this.items = [
      {label: 'Strona główna', routerLink: [''], icon: 'fa fa-fw fa-bar-chart'},
      {label: 'Rejestracja praktykanta', routerLink: ['app/new-intern'], icon: 'fa fa-fw fa-calendar'},
      {label: 'Spis firm', icon: 'fa fa-fw fa-book'},
      {label: 'Spis praktykantów', icon: 'fa fa-fw fa-support'},
      {label: 'Ankiety', routerLink: ['app/forms'], icon: 'fa fa-fw fa-twitter'},
      {label: 'Statystki', routerLink: ['app/statistics'], icon: 'fa fa-fw fa-twitter'},
      {label: 'Wyloguj', routerLink: ['app/logout'], icon: 'fa fa-fw fa-twitter'}
    ];

    this.activeItem = this.items[0];
  }

  closeItem(event, index) {
    this.items = this.items.filter((item, i) => i !== index);
    event.preventDefault();
  }

}
