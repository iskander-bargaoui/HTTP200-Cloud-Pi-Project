import { Component } from '@angular/core';
import { Reservation } from 'src/app/Models/reservation';
import { ReservationimplService } from 'src/app/Services/reservationimpl.service';
@Component({
  selector: 'app-allres',
  templateUrl: './allres.component.html',
  styleUrls: ['./allres.component.css']
})
export class AllresComponent {
  reservations :Reservation [] = [] ;
  selectedReservation :any ={} ;
  newReservation:any ={} ;
  constructor(private reservationimplService :ReservationimplService){}

  ngOnInit() {
    this.reservationimplService.getAllRes().subscribe(reservations => {
      this.reservations = reservations;
    });
  }  

  getAllRes() {
    this.reservationimplService.getAllRes().subscribe(reservations => {
      this.reservations = reservations;
    });
  }

  selecteReservation(reservation: any) {
    this.selectedReservation = reservation;
  }

  updateRes() {
    this.reservationimplService.updateRes(this.selectedReservation).subscribe(res => {
      this.selectedReservation = {};
    });
  }

  deleteRes(id: number) {
    this.reservationimplService.deleteRes(id).subscribe(res => {
      this.reservations = this.reservations.filter(p => p.idReservation !== id);
    });
  }
}
