import { Component, OnInit } from '@angular/core';
import { AuthService } from '../Services/auth.service';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit{

  isSuccessful = false;
   
  msg = '';
  form: any = {};
  email!: string;
  constructor(private userservice: AuthService
   )
     { 
      this.email = "";
    }

  
    ngOnInit(): void {
      
    }

    forgotPassword() {
      this.userservice.forgotPassword(this.email).subscribe(
        data => {
          console.log(data)
          this.msg = 'Mail Sent Succesfully';
          this.email = "";
          this.isSuccessful = true;
          
        },
        error => {
          console.log(error)
          this.msg = 'cannot found user with this email';
          this.email = "";
          this.isSuccessful = false;

        });
    }
    
    triggerWindow():void{
      window.alert("Sending Email! Please wait!");
    }
  
    
}
