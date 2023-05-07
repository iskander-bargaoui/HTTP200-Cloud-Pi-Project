import { Component } from '@angular/core';
import { FormationService } from 'src/app/Services/formation.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EvenementService } from 'src/app/Services/evenement.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Formation } from 'src/app/Models/formation';

@Component({
  selector: 'app-formation',
  templateUrl: './formation.component.html',
  styleUrls: ['./formation.component.css']
})
export class FormationComponent {
  evenements: Formation[] = [];
  newEvenement: Formation = new Formation();
  formationForm: FormGroup = this.fb.group({
    titreFormation: ['', Validators.minLength(5)],
    dateDebutFor: ['', Validators.required],
    dateFinFor: ['', Validators.required],
    description: ['', Validators.minLength(5)],
    organisateur: ['', Validators.required],

  });
 


  constructor(private fb: FormBuilder, private formationService: FormationService , private router: Router , private evenementservice : EvenementService , private http: HttpClient) { }
  

  ngOnInit(): void {
   
    this.getEvents();
   
  }

  


  getEvents(): void {
    this.formationService.getAllFormations()
      .subscribe(evenement => this.evenements = evenement);
  }

  addFormation() {
    this.newEvenement = this.formationForm.value; // set newEvenement to the form value
    this.formationService.addFormation(this.newEvenement).subscribe(res => {
      this.evenements.push(res);
      this.formationForm.reset();
      this.newEvenement = new Formation(); // reset newEvenement to a new instance
    });
    this.router.navigate(['/user/formationlist']);
  }

  onIA() {
    const url = 'http://localhost:5000/IAVOICE'; // Replace with your Flask API URL
    this.http.get(url).subscribe(result => {
        console.log(result);
    });
  
  }


  
}
