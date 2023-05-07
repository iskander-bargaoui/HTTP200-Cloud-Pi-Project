import { Component } from '@angular/core';
import { NotificationServiceService } from 'src/app/Services/notification-service.service';
import { Notification } from 'src/app/Models/notification';


@Component({
  selector: 'app-allnot',
  templateUrl: './allnot.component.html',
  styleUrls: ['./allnot.component.css']
})
export class AllnotComponent {
  notifications :Notification [] = [] ;
  selectedNotification :any ={} ;
  newNotification:any ={} ;
  constructor(private notificationservice :NotificationServiceService){}

  ngOnInit() {
    this.notificationservice.getAllNot().subscribe(notifications => {
      this.notifications = notifications;
    });
  }  

  getAllNot() {
    this.notificationservice.getAllNot().subscribe(notifications => {
      this.notifications = notifications;
    });
  }

  selecteNotification(notification: any) {
    this.selectedNotification = notification;
  }

  updateNot() {
    this.notificationservice.updateNot(this.selectedNotification).subscribe(res => {
      this.selectedNotification = {};
    });
  }

  deleteNot(id: number) {
    this.notificationservice.deleteNot(id).subscribe(res => {
      this.notifications = this.notifications.filter(p => p.idNotification !== id);
    });
  }
}
