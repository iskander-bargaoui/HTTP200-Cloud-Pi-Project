import { Component, OnInit } from '@angular/core';
import { AuthService } from '../Services/auth.service';
import { StorageService } from '../Services/storage.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  protected aFormGroup!: FormGroup;
  token: string|undefined;
  siteKey:string="6LdQndclAAAAADdZYPbes3c-tYXuRILVWVsFGUg3";
  secretKey:string="6LeIxAcTAAAAAGG-vFI1TnRWxMZNFuojJ4WifJWe"; 
  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isSuccessful = false;
  isLoginFailed = false;
  isSigninFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: AuthService, private storageService: StorageService, private formBuilder: FormBuilder) {    this.token = undefined;
  }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
    }
    // Recaptcha
    this.aFormGroup = this.formBuilder.group({
      recaptcha: ['', Validators.required]
    });
  }

  onSubmit(): void {
    const { username, password } = this.form;

    this.authService.login(username, password).subscribe({
      next: data => {
        console.log(data);
        localStorage.setItem('token',data['accessToken']);
        this.storageService.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.isSuccessful = true;
        this.isSigninFailed = false;
        this.roles = this.storageService.getUser().roles;
        this.reloadPage();
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
        this.isSigninFailed = true;
      }
    });
  }
  reloadPage(): void {
    window.setTimeout(() => {
      window.location.replace("/user/home");
    }, 1700); // 3000 milliseconds = 3 seconds
  }
}