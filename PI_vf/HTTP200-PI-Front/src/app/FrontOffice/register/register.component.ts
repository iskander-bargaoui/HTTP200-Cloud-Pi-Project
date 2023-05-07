import { Component, OnInit } from '@angular/core';
import { AuthService } from '../Services/auth.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {
    nom: null,
    firstname: null,
    lastname: null,
    email: null,
    phonenumber:null,
    birthday:null,
    password: null

  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const { username, nom, prenom, email, phoneNumber, birthDate, password } = this.form;

    this.authService.register(username, nom, prenom, email, phoneNumber, birthDate, password).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.reloadPage();
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });
  }
  
  reloadPage(): void {
      window.setTimeout(() => {
      window.location.replace("/user/home");
    }, 2500); // 3000 milliseconds = 3 seconds
  }
  
  triggerWindow():void{
    window.alert("Sending Email! Please wait!");
  }
}
