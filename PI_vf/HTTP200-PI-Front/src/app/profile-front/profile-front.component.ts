import { Component } from '@angular/core';
import { Profile } from '../Models/Profile';
import { ProfileService } from '../Services/profile.service';
import { Feedback } from '../Models/Feedback';
import { FeedbackService } from '../Services/feedback.service';
import { HttpClient } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { Subject } from 'rxjs';
import { PublicationService } from '../Services/publication.service';
import { Publication } from '../Models/publication';
declare var $: any;
@Component({
  selector: 'app-profile-front',
  templateUrl: './profile-front.component.html',
  styleUrls: ['./profile-front.component.css']
})
export class ProfileFrontComponent { 
  myInput: string = "";
  profiles: Profile[] = [];
  Feedbacks : Feedback[] = [];
  selectedProfile: any = {};
  selectedFeedback: any = {};
  newProfile: any = {}; 
  stars: number[] = [1, 2, 3, 4, 5];
  selectedValue!: number;
  feedback : Feedback[] = [];
  analysisImage !: string;
  selectedFile!: File; 
  newFeedback: any = {};
  newRating: number = 0;
  showEditProfilePopup = false;
  showOtherPopup = false;
  photoprofile!: string;
  selectedProfileSubject: Subject<Profile> = new Subject<Profile>();
  favoritePublications!: Publication[];

  constructor(private profileService: ProfileService,private FeedbackService: FeedbackService,private http: HttpClient, private publicationService:PublicationService) { }
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
    this.FeedbackService.getFeedbacksByProfileUsername(profile.username).subscribe(feedbacks => {
      this.selectedProfile.feedbackList = feedbacks;
    }); 
    console.log(this.selectedProfile);
  } 


/*
selectProfile(profile: any) {
  let feedbackList = [];
  for (let feedback of profile.feedbackList) {
    feedbackList.push({message: feedback.message, rating: feedback.rating});
  }

  this.selectedProfile = {
    id: profile.id,
    username: profile.username,
    sexe: profile.sexe,
    categorie: profile.categorie,
    photoprofile: profile.photoprofile,
    feedbackList: feedbackList
  };

  console.log(this.selectedProfile);
}
*/

  updateProfile() {
    console.log(this.selectedProfile);
    this.profileService.updateProfile(this.selectedProfile).subscribe(res => {
      this.selectedProfile = {};
    });
 
  }
  editProfile() {
    $('#exampleModal').modal('hide'); // Hide the feedback modal
    $('#editProfileModal').modal('show'); // Show the edit profile modal
    // Pass the selected profile object to the edit profile modal
    this.selectedProfileSubject.next(this.selectedProfile); 
  }
  

  deleteProfile(id: number) {
    this.profileService.deleteProfile(id).subscribe(res => {
      this.profiles = this.profiles.filter(p => p.id !== id);
    });
  } 

  hidepopup(){
    $('#exampleModal').modal('hide');
  }
  
deleteFeedback(id: number) {
  this.profileService.deleteFeedback(id).subscribe(res => {
    this.Feedbacks = this.Feedbacks.filter(p => p.id !== id);
    window.location.reload();
  });
} 
selectFeedback(feedback: any) {
  this.selectFeedback = feedback;
} 
countStar(star: number, newFeedback: Feedback): Feedback {
  newFeedback.rating = star;
  newFeedback.profile = this.selectedProfile;
  console.log('Value of feedback1', newFeedback); 
  console.log('Value of Profileselected', this.selectedProfile);
  console.log('Value of star', star);   
  return newFeedback;
} 


setRating(rating: number) {
  this.newFeedback.rating = rating;
}
addFeedback() {
  if (this.newFeedback.rating && this.newFeedback.message) {
    this.newFeedback.createdDate = Date.now();
    this.newFeedback.updatedDate = Date.now();
    console.log(this.newFeedback);
    this.FeedbackService.addFeedback(this.newFeedback).subscribe(res => {
      this.Feedbacks.push(res);
      this.newFeedback = {};
    });

  }
}


feedbackAnalysis(username:string){
  this.FeedbackService.FeedbackAnalysis(username).subscribe((image: Blob) => {
    const reader = new FileReader();
    reader.readAsDataURL(image);
    reader.onload = () => {
      if (reader.result !== null) {
        this.analysisImage = reader.result as string;
      } 
      console.log(this.analysisImage);
    };
  }); 
}

 onSubmit(event: Event) {
    const fileInput = event.target as HTMLInputElement;
    if (!fileInput.files || fileInput.files.length === 0) {
      console.log('No file selected');
      return;
    }
    this.selectedFile = fileInput.files[0];
    this.selectedProfile.photoprofile = "/assets/images/"+this.selectedFile.name;
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

  getFavoritePublications(): void {
    this.publicationService.getFavoritePublicationsByUserId()
      .subscribe(favoritePublications => this.favoritePublications = favoritePublications);
  }

}
