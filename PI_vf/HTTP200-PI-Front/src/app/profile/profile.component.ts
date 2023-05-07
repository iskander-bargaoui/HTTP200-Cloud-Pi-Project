import { Component } from '@angular/core';
import { ProfileService } from '../Services/profile.service';
import { HttpClient } from '@angular/common/http';
import { Validators, FormGroup, FormControl } from '@angular/forms';
import { StorageService } from '../FrontOffice/Services/storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  profiles: any[] = [];
  selectedProfile: any = {};
  newProfile: any = {};
  selectedFile!: File;
  photoprofile!: string;
  profileForm!: FormGroup;

  constructor(private profileService: ProfileService,private http: HttpClient) { }
/*
 onFileChanged(event: Event) {
  const inputElement = event.target as HTMLInputElement;
  if (inputElement.files && inputElement.files.length > 0) {
    this.selectedFile = inputElement.files[0];
    this.newProfile.photoprofile = "/assets/images/"+this.selectedFile.name;
    console.log(this.selectedFile.name);
  } 
*/



  
  ngOnInit(): void {
   // this.storage_service.getUser();
   //  console.log("accessToken",this.storage_service.getUser());
   const token = localStorage.getItem('token');
  console.log(token); 
   this.getProfiles();
    this.profileForm = new FormGroup({
      'username': new FormControl(null, Validators.required),
      'sex': new FormControl(null, Validators.required),
      'category': new FormControl(null, Validators.required)
    }); 

  }

  getProfiles() {
    this.profileService.getProfiles().subscribe(res => {
      this.profiles = res;
    });
  }

  selectProfile(profile: any) {
    this.selectedProfile = profile;
  }

  addProfile() {
    this.profileService.addProfile(this.newProfile).subscribe(res => {
      this.profiles.push(res);
      this.newProfile = {};
    });
  }
  onSubmit(event: Event) {
    const fileInput = event.target as HTMLInputElement;
    if (!fileInput.files || fileInput.files.length === 0) {
      console.log('No file selected');
      return;
    }
    this.selectedFile = fileInput.files[0];
    this.newProfile.photoprofile = "/assets/images/"+this.selectedFile.name;
    console.log(this.selectedFile.name);
    const formData = new FormData();
    formData.append('file', fileInput.files[0]);
    this.http.post<any>('http://localhost:8080/api/uploadImage', formData).subscribe(
      (res) => {
        this.photoprofile = res.imagePath;
        console.log(this.photoprofile);
      },
      (err) => {
        console.log(err);
      } 
    );
  }
  

  
}
