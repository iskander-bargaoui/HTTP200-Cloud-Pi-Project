import { Component } from '@angular/core';
import { Reservation } from 'src/app/Models/reservation';
import { ReservationimplService } from 'src/app/Services/reservationimpl.service';
@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent {
  reservation :Reservation [] = [] ;
  selectedReservation :any ={} ;
  newReservation:any ={} ;
  constructor(private ReservationimplService :ReservationimplService){}

 

  AddRes(formValue: any) :void {
  
    this.ReservationimplService.addRes(this.newReservation).subscribe(newReservation => {
      this.newReservation = new Reservation();
      
        });
      }
}
