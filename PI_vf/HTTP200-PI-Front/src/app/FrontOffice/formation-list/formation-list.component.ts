import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FormationService } from 'src/app/Services/formation.service';
import { Formation } from 'src/app/Models/formation';

@Component({
  selector: 'app-formation-list',
  templateUrl: './formation-list.component.html',
  styleUrls: ['./formation-list.component.css']
})
export class FormationListComponent {
  formations: Formation[] = [];
  selectedEvent: any = {};
  constructor(private fb: FormBuilder, private formationService: FormationService , private http: HttpClient)  { }

  ngOnInit(): void {
   
    this.formationService.getAllFormations().subscribe((data: any) => {
      this.formations = data;
    });
  }

  getFormations(): void {
    this.formationService.getAllFormations()
      .subscribe(formation => this.formations = formation);
  }

  deleteFormation(id: number) {
    this.formationService.deleteFormation(id).subscribe(res => {
      this.formations = this.formations.filter(p => p.idFormation !== id);
    });
  }

  selecteEvent(formation : any){
    this.selectedEvent = formation;
  }

  updateFormation() {
    this.formationService.updateFormation(this.selectedEvent).subscribe(res => {
      this.selectedEvent = {};
    });
  }




}




