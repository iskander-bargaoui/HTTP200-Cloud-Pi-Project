import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reservation } from '../Models/reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationimplService {
  constructor(private http: HttpClient) { } 

  private endpoint = 'http://localhost:8080/api';

  getAllRes(): Observable<any> {
    const url = `${this.endpoint}/GetAllRes`;
    return this.http.get(url);
  }

addRes(reservation: Reservation): Observable<any> {
  const url = `${this.endpoint}/AddRes`;
  return this.http.post<Reservation>(url, reservation);
}

updateRes(reservation: Reservation): Observable<any> {
  const url = `${this.endpoint}/UpdateRes`;
  return this.http.put(url, reservation);
}

deleteRes(id: number): Observable<any> {
  const url = `${this.endpoint}/DeleteRes/${id}`;
  return this.http.delete(url);
}
}



  
