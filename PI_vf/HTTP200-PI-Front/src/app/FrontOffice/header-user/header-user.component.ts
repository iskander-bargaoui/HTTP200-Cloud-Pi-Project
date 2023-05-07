import { Component, OnInit } from '@angular/core';
import { StorageService } from '../Services/storage.service';
import { AuthService } from '../Services/auth.service';
import { EventBusService } from '../_shared/event-bus.service';

@Component({
  selector: 'app-header-user',
  templateUrl: './header-user.component.html',
  styleUrls: ['./header-user.component.css']
})

export class HeaderUserComponent implements OnInit {

  user:any;
  connectedUser:any;

  constructor(
    private storageService: StorageService,
    private authService: AuthService,
  ) {}


  ngOnInit(): void {
    // Session
    this.user=this.storageService.getUser();
    console.log(this.user);
    console.log(this.user.id);

    //  this.storageService.getUser().subscribe({
    //   next: (data: any) => {
    //     console.log("*");
    //     console.log(data);
    //   }

    // Connected User
    this.connectedUser=this.storageService.isLoggedIn();
    console.log(this.connectedUser);

  };
  



  
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
}