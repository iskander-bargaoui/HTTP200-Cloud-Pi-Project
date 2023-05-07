import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/FrontOffice/Services/auth.service';
import { UserService } from 'src/app/FrontOffice/Services/user.service';
import { User } from 'src/app/Models/user';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit{
  
  user: User = new User();

  constructor(private userService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }


  saveUser(){
    this.userService.addUser(this.user).subscribe( data =>{
      console.log(data);
      this.goToUserList();

    },
    error => console.log(error));
  }

  goToUserList(){
    this.router.navigate(['/usersList']);
  }

  onSubmit(){
    console.log(this.user);
    this.saveUser();
  }
}
