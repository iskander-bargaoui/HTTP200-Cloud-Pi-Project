import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EvenementService } from 'src/app/Services/evenement.service';
import { HttpClient } from '@angular/common/http';
import { Evenement } from 'src/app/Models/evenement';



@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent {
  evenements: Evenement[] = [];
  selectedEvent: any = {};

  constructor(private fb: FormBuilder, private evenementService: EvenementService , private http: HttpClient)  { }
  

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
    this.evenementService.deleteEvent(id).subscribe(res => {
      this.evenements = this.evenements.filter(p => p.idEvenement !== id);
    });
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

  downloadPDF() {
    this.evenementService.generateEventsPDF().subscribe((data: Blob) => {
      const blob = new Blob([data], { type: 'application/pdf' });
      const url = window.URL.createObjectURL(blob);
      window.open(url);
    });
  }
  
   
 }
