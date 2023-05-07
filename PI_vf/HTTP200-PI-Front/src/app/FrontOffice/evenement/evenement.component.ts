import { EvenementService } from 'src/app/Services/evenement.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Evenement } from 'src/app/Models/evenement';


@Component({
  selector: 'app-evenement',
  templateUrl: './evenement.component.html',
  styleUrls: ['./evenement.component.css']
})

export class EvenementComponent implements OnInit {
  evenements: Evenement[] = [];
  newEvenement: Evenement = new Evenement();
  evenementForm: FormGroup = this.fb.group({
    lieu: ['', Validators.required],
    dateDebut: ['', Validators.required],
    dateFin: ['', Validators.required],
    description: ['', Validators.required],
    titreEvennement: ['', Validators.required],
    rating:['', Validators.required]
  });

  constructor(private fb: FormBuilder, private evenementService: EvenementService , private router: Router , private http: HttpClient) { }
  

  ngOnInit(): void {
   
    this.getEvents();
  }

  getEvents(): void {
    this.evenementService.getAllEvents()
      .subscribe(evenement => this.evenements = evenement);
  }

  addEvenement() {
    this.newEvenement = this.evenementForm.value; // set newEvenement to the form value
    this.evenementService.addEvenement(this.newEvenement).subscribe(res => {
      this.evenements.push(res);
      this.evenementForm.reset();
      this.newEvenement = new Evenement(); // reset newEvenement to a new instance
      this.router.navigate(['/user/eventlist']);

    });
  }
  onCapture() {
    const url = 'http://localhost:5000/my-python-api'; // Replace with your Flask API URL
    this.http.get(url).subscribe(result => {
        console.log(result);
    });
  
  }

  

  

}
