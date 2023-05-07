import { Component, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { AuthService } from 'src/app/FrontOffice/Services/auth.service';
import { StorageService } from 'src/app/FrontOffice/Services/storage.service';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit{
  
  user:any;

  constructor(private userService: AuthService, private router: Router, private storageService:StorageService) { }

  ngOnInit(): void {
    this.user=this.storageService.getUser();
    console.log(this.user);
  }


  saveUser(){
    this.userService.addUser(this.user).subscribe( data =>{
      console.log(data);
      this.goToUserList();
//      this.goToUserList();
//this.router.navigateByUrl('/user/home');
window.location.replace("/user/home");

    },
    error => console.log(error));
  }

  goToUserList(){
    this.router.navigate(['/usersList']);
  }

  onSubmit(){
    console.log(this.user);
    this.saveUser();

    this.router.navigateByUrl('/user/home');
  }
}
