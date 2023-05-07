export class ChatMessage {
    id: number;
    senderId: number;
    receiverId: number;
    sender: string;
    receiver: string;
    content: string;
    time: Date;
  
    constructor(id: number, senderId: number, receiverId: number, sender: string, receiver: string, content: string, time: Date) {
      this.id = id;
      this.senderId = senderId;
      this.receiverId = receiverId;
      this.sender = sender;
      this.receiver = receiver;
      this.content = content;
      this.time = time;
    }
  }