import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Publication } from '../Models/publication';
import { map } from 'rxjs';
import { HttpEventType } from '@angular/common/http';
import { Visibilite } from '../Models/visibilite';
import { LocalDateTime,LocalDate } from '@js-joda/core';




@Injectable({
  providedIn: 'root'
})
export class PublicationService {

  

  private baseUrl = 'http://localhost:8080/api/publications';

  constructor(private http: HttpClient) { }

 /* addPublication(pub: Publication, idUser: number, file: File): Observable<Publication> {
    const formData = new FormData();
    formData.append('pub', JSON.stringify(pub));
    formData.append('file', file);
  
    return this.http.post<Publication>(`${this.baseUrl}/AddPublication/${idUser}`, formData);
  }
  

  /*addPublication(pub: Publication,idUser: number, multipartFile: File): Observable<Publication> {
    const formData: FormData = new FormData();
    formData.append('pub', JSON.stringify(pub));
    formData.append('file', multipartFile, multipartFile.name);

    const headers = new HttpHeaders();
    headers.append('Accept', 'application/json');

    return this.http.post<Publication>(`${this.baseUrl}/AddPublication/${idUser}`, formData,  { headers });
  }*/

 addPublication(publication: Publication): Observable<Publication> {
    return this.http.post<Publication>(`${this.baseUrl}/AddPublication`, publication);
  }

  updatePublication(idPub: number, publication: Publication): Observable<any> {
    return this.http.put(`${this.baseUrl}/UpdatePublication/${idPub}`, publication);
  }

   /* addPub(titrePub: string, contenuPub: string, vis: Visibilite, dateCreationPub: LocalDate, imageData: File, likeCount: number, dislikeCount: number, isFavorite: boolean, favoriteDate:LocalDateTime, idUser:number): Observable<Publication> {
    const formData = new FormData();
    formData.append('titrePub', titrePub);
    formData.append('contenuPub', contenuPub);
    formData.append('vis', vis);
    formData.append('dateCreationPub', dateCreationPub.toString());
    formData.append('likeCount', likeCount.toString());
    formData.append('dislikeCount', dislikeCount.toString());
    formData.append('isFavorite', isFavorite.toString());
    formData.append('favoriteDate', favoriteDate.toString());
    //formData.append('user', user);
    formData.append('imageData', imageData, imageData.name);

    return this.http.post<Publication>(`${this.baseUrl}/AddPublication/${idUser}`, formData);
  }

  updatePublication(idPub: number, titrePub: string, contenuPub: string, vis: Visibilite, dateCreationPub: LocalDate, imageData: File, likeCount: number, dislikeCount: number, isFavorite: boolean, favoriteDate: LocalDateTime, idUser: number): Observable<Publication> {
    const formData = new FormData();
    formData.append('idPub', idPub.toString());
    formData.append('titrePub', titrePub);
    formData.append('contenuPub', contenuPub);
    formData.append('vis', vis);
    formData.append('dateCreationPub', dateCreationPub.toString());
    formData.append('imageData', imageData);
    formData.append('likeCount', likeCount.toString());
    formData.append('dislikeCount', dislikeCount.toString());
    formData.append('isFavorite', isFavorite.toString());
    formData.append('favoriteDate', favoriteDate.toString());
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'multipart/form-data'
      })
    };
    return this.http.put<Publication>(`${this.baseUrl}/UpdatePublication/${idPub}`, formData,httpOptions);
  }*/
  
  getPublicationById(idPub: number): Observable<Publication> {
    return this.http.get<Publication>(`${this.baseUrl}/GetPublicationByID/${idPub}`);
  }

  getAllPublications(): Observable<Publication[]> {
    return this.http.get<Publication[]>(`${this.baseUrl}/GetAllPublication`);
  }

  deletePublication(idPub: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/DeletePublication/${idPub}`, { responseType: 'text' });
  }

  getPublicationsByUserId(): Observable<Publication[]> {
    return this.http.get<Publication[]>(`${this.baseUrl}/RetrievePublicationUserById`);
  }

  toggleFavoritePublication( idPub: number) {
    return this.http.put<Publication>(`${this.baseUrl}/toggleFavoritePublication/${idPub}`, {});
  }

  getFavoritePublicationsByUserId() {
    return this.http.get<Publication[]>(`${this.baseUrl}/getFavoritePublicationsByUserId`);
  }
  
  
  
}
