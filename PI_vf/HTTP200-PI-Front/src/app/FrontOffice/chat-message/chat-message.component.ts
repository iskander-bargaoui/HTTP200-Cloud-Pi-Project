import { Component, OnInit } from '@angular/core';
import { ChatimplService } from 'src/app/Services/chatimpl.service';
import { ChatMessage } from 'src/app/Models/chat';


@Component({
  selector: 'app-chat-message',
  templateUrl: './chat-message.component.html',
 // styleUrls: ['./chat.component-message.css']
})
export class ChatComponent implements OnInit {

  public messages: ChatMessage[] = [];
  public newMessage: ChatMessage = new ChatMessage(0, 0, 0, '', '', '', new Date());

  constructor(private chatService: ChatimplService) { }

  ngOnInit(): void {
    this.loadMessages();
  }

  public sendMessage(): void {
    this.chatService.sendMessage(this.newMessage).subscribe(() => {
      this.newMessage = new ChatMessage(1, 2, 3, 'John', 'Mary', 'Hello, how are you?', new Date());
      this.loadMessages();
    });
  }

  private loadMessages(): void {
    this.chatService.retrieveMessages(1, 2).subscribe((messages: ChatMessage[]) => {
      this.messages = messages;
    });
  }

}
