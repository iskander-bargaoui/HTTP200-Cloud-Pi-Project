import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ChatMessage } from '../Models/chat';


@Injectable({
  providedIn: 'root'
})
export class ChatimplService {

  private baseUrl = 'http://localhost:8080'; // Replace with your Spring Boot API URL

  constructor(private http: HttpClient) { }

  public sendMessage(message: ChatMessage): Observable<ChatMessage> {
    return this.http.post<ChatMessage>(`${this.baseUrl}/AddMesg`, message);
  }

  public retrieveMessages(senderId: number, receiverId: number): Observable<ChatMessage[]> {
    return this.http.get<ChatMessage[]>(`${this.baseUrl}/retrieveByIds/${senderId}/${receiverId}`);
  }
}
