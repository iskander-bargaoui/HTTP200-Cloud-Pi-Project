import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { EvenementService } from 'src/app/Services/evenement.service';
import { Router } from '@angular/router';
import { Evenement } from 'src/app/Models/evenement';

@Component({
  selector: 'app-evenement-backend',
  templateUrl: './evenement-backend.component.html',
  styleUrls: ['./evenement-backend.component.css']
})

export class EvenementBackendComponent implements OnInit{
  evenements: Evenement[] = [];
  selectedEvent: any = {};

  constructor(private fb: FormBuilder, private evenementService: EvenementService) { }
  

  ngOnInit(): void {
   
    this.evenementService.getAllEvents().subscribe((data: any) => {
      this.evenements = data;
    });
  }
  getPublications(): void {
    this.evenementService.getAllEvents()
      .subscribe(evenement => this.evenements = evenement);
  }
  

  deleteEvent(id: number) {
    if (confirm('Are you sure you want to delete this event?')) {
    this.evenementService.deleteEvent(id).subscribe(res => {
      this.evenements = this.evenements.filter(p => p.idEvenement !== id);
    });
  }
  }

  selecteEvent(evenement : any){
    this.selectedEvent = evenement;
  }
  updateEvenement() {
    this.evenementService.updateEvenement(this.selectedEvent).subscribe(res => {
      this.selectedEvent = {};
    });
  }

  rateEvent(rating: number) {
    // Store the rating value in a variable or send it to the server
    console.log('Rated event with ' + rating + ' stars');
  }
  
}
