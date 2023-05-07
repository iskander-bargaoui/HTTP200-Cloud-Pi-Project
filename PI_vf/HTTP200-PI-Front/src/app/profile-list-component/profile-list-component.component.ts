import { Component } from '@angular/core';
import { ProfileService } from '../Services/profile.service';
import { Profile } from '../Models/Profile';
import { FeedbackService } from '../Services/feedback.service';
import { Feedback } from '../Models/Feedback';

@Component({
  selector: 'app-profile-list-component',
  templateUrl: './profile-list-component.component.html',
  styleUrls: ['./profile-list-component.component.css']
})
export class ProfileListComponentComponent {   
  profiles: Profile[] = [];
  Feedbacks : Feedback[] = [];
  selectedProfile: any = {};
  newProfile: any = {};

  constructor(private profileService: ProfileService,private FeedbackService: FeedbackService) { }

  ngOnInit() {
    this.profileService.getProfiles().subscribe(profiles => {
      this.profiles = profiles;
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

  updateProfile() {
    this.profileService.updateProfile(this.selectedProfile).subscribe(res => {
      this.selectedProfile = {};
    });
  }

  deleteProfile(id: number) {
    this.profileService.deleteProfile(id).subscribe(res => {
      this.profiles = this.profiles.filter(p => p.id !== id);
    });
  } 

  
deleteFeedback(id: number) {
  this.profileService.deleteFeedback(id).subscribe(res => {
    this.Feedbacks = this.Feedbacks.filter(p => p.id !== id);
    window.location.reload();
  });
}



}
