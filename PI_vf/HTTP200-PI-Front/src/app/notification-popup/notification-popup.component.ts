import { Component, OnInit } from '@angular/core';
import { NotificationServiceService } from '../Services/notification-service.service';
import { Notification } from '../Models/notification';
import { HttpClient } from '@angular/common/http';
import { Reservation } from '../Models/reservation';
import { ToastrService } from 'ngx-toastr';


declare var $:any;


@Component({
  selector: 'app-notification-popup',
  templateUrl: './notification-popup.component.html',
  styleUrls: ['./notification-popup.component.css']
})
export class NotificationPopupComponent  {


  notifications: Reservation[] = []; 

  constructor(private notificationService: NotificationServiceService, private toaster : ToastrService) { } ;

  showtoast()
  {
    this.toaster.success("dont forgate that your reservation")
  }

  ngOnInit() {
    this.notificationService.getNotifications().subscribe(notifications => {
      this.notifications = notifications});
  }

  

  getNotifications(){
    this.notificationService.getNotifications().subscribe(notifications => {
        this.notifications = notifications;
      },
    );
  }
}