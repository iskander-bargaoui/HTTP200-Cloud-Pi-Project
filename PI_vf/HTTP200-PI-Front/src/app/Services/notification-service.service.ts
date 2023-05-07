import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Notification } from '../Models/notification';
@Injectable({
  providedIn: 'root'
})
export class NotificationServiceService {
  

  private notificationsUrl = 'http://localhost:8080/api'; 
  

  constructor(private http: HttpClient) { }
  getNotifications(): Observable<any> {
    const url = `${this.notificationsUrl}/Getremainder`;
    return this.http.get(url); 
  }


  getAllNot(): Observable<any> {
    const url = `${this.notificationsUrl}/GetAllNot`;
    return this.http.get(url);
  }

addNot(notification: Notification): Observable<any> {
  const url = `${this.notificationsUrl}/AddNot`;
  return this.http.post<Notification>(url,notification);
}

updateNot(notification: Notification): Observable<any> {
  const url = `${this.notificationsUrl}/UpdateNot`;
  return this.http.put(url,notification);
}

deleteNot(id: number): Observable<any> {
  const url = `${this.notificationsUrl}/DeleteNot/${id}`;
  return this.http.delete(url);
}
  }

  

