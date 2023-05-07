import { Component } from '@angular/core';
import { Feedback } from '../Models/Feedback';
import { FeedbackService } from '../Services/feedback.service';

@Component({
  selector: 'app-feedback-back',
  templateUrl: './feedback-back.component.html',
  styleUrls: ['./feedback-back.component.css']
})
export class FeedbackBackComponent {
  Feedbacks: Feedback[] = [];
  selectedFeedback: any = {};
  newFeedback: any = {};

  constructor(private FeedbackService: FeedbackService) { }

  ngOnInit() {
    this.FeedbackService.getFeedbacks().subscribe(Feedbacks => {
      this.Feedbacks = Feedbacks;
    });
  }  

  getProfiles() {
    this.FeedbackService.getFeedbacks().subscribe(res => {
      this.Feedbacks = res;
    });
  }

  selectFeedback(Feedback: any) {
    this.selectedFeedback = Feedback;
  }

  updateFeedback() {
    this.FeedbackService.updateFeedback(this.selectedFeedback).subscribe(res => {
      this.selectedFeedback = {};
    });
  }

  deleteFeedback(id: number) {
    this.FeedbackService.deleteFeedback(id).subscribe(res => {
      this.Feedbacks = this.Feedbacks.filter(f => f.id !== id);
    });
  }
}
