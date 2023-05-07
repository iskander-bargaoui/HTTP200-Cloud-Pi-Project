import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Formation } from 'src/app/Models/formation';
import { FormationService } from 'src/app/Services/formation.service';

@Component({
  selector: 'app-formation-back',
  templateUrl: './formation-back.component.html',
  styleUrls: ['./formation-back.component.css']
})
export class FormationBackComponent {
  evenements: Formation[] = [];
  selectedEvent: any = {};

  constructor(private fb: FormBuilder, private formationService: FormationService) { }
  ngOnInit(): void {
   
    this.formationService.getAllFormations().subscribe((data: any) => {
      this.evenements = data;
    });
  }

  getPublications(): void {
    this.formationService.getAllFormations()
      .subscribe(evenement => this.evenements = evenement);
  }

  deleteEvent(id: number) {
    if (confirm('Are you sure you want to delete this event?')) {
    this.formationService.deleteFormation(id).subscribe(res => {
      this.evenements = this.evenements.filter(p => p.idFormation !== id);
    });
  }
  }

  selecteEvent(evenement : any){
    this.selectedEvent = evenement;
  }
  updateFormation() {
    this.formationService.updateFormation(this.selectedEvent).subscribe(res => {
      this.selectedEvent = {};
    });
  }

}
