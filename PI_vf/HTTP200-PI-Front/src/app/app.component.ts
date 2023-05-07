import { Component, OnInit } from '@angular/core';
import { StorageService } from './FrontOffice/Services/storage.service';
import { AuthService } from './FrontOffice/Services/auth.service';
import { EventBusService } from './FrontOffice/_shared/event-bus.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'body',
  template: '<router-outlet></router-outlet>',
  //styleUrls: ['./app.component.css']
})

export class AppComponent  implements OnInit{
  title = 'FrontWebProject';
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;
  eventBusSub?: Subscription;

  constructor(
    private storageService: StorageService,
    private authService: AuthService,
    private eventBusService: EventBusService
  ) {}

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();

    if (this.isLoggedIn) {
      const user = this.storageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_WORKER');

      this.username = user.username;
    }

    this.eventBusSub = this.eventBusService.on('logout', () => {
      this.logout();
    });
  }

  logout(): void {
    this.authService.logout().subscribe({
      next: res => {
        console.log(res);
        this.storageService.clean();

        window.location.reload();
      },
      error: err => {
        console.log(err);
      }
    });
  }
 // styleUrls: ['./app.component.css']
}
