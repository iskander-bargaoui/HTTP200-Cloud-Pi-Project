import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {  Observable, throwError } from 'rxjs';
import { Evenement } from '../Models/evenement';

@Injectable({
  providedIn: 'root'
})
export class EvenementService {
  private baseUrl  = 'http://localhost:8080/api/evenement/'; 
  constructor(private http: HttpClient) { }

 addEvenement(evenement: Evenement): Observable<any> {
    const url = `${this.baseUrl}addEvenement`;
    return this.http.post(url, evenement);
}

  updateEvenement(evenement: Evenement): Observable<any> {
    const url = `${this.baseUrl}updateEvenement`;
    const headers = { 'Content-Type': 'application/json' };

    return this.http.put(url, evenement ,{ headers });
  }

  getPublicationById(idEvenement: number): Observable<Evenement> {
    return this.http.get<Evenement>(`${this.baseUrl}getEvenementbyid/${idEvenement}`);
  }

  

  getAllEvents(): Observable<Evenement[]> {
    return this.http.get<Evenement[]>(`${this.baseUrl}listEvenement`);
  }

  
  deleteEvent(idEvenement: number): Observable<any> {
    const url = `${this.baseUrl}deleteEvenement/${idEvenement}`;
    return this.http.delete(url);
  }

  generateEventsPDF() {
    const url = `${this.baseUrl}events/pdf`;
    return this.http.get(url, { responseType: 'blob' });
  }

  onCapture() {
    const url = 'http://localhost:5000/my-python-api'; 
    this.http.get(url).subscribe(result => {
        console.log(result);
    });
}
  }

  



