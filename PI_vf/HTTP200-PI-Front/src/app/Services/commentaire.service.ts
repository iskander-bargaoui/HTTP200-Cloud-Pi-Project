import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Commentaire } from '../Models/commentaire';

@Injectable({
  providedIn: 'root'
})
export class CommentaireService {
  private baseUrl = 'http://localhost:8080/api/commentaire';

  constructor(private http: HttpClient) { }

  updateCommentaire(idComm: number, commentaire: Commentaire): Observable<Commentaire> {
    return this.http.put<Commentaire>(`${this.baseUrl}/UpdateCommentaire/${idComm}`, commentaire);
  }

  retrieveCommentaireByPubId(idPub: number): Observable<Commentaire[]> {
    return this.http.get<Commentaire[]>(`${this.baseUrl}/RetrieveCommentaireByPubId/${idPub}`);
  }

  assignCommentaireToPub(commentaire: Commentaire,idPub:number): Observable<Commentaire> {
    return this.http.post<Commentaire>(`${this.baseUrl}/AssignCommentaireToPub/${idPub}`, commentaire);
  }

  deleteCommentaire(idComm: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/DeleteCommentaire/${idComm}`);
  }

  countCommentsInPublication(idPub: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/CountCommentsInPublication/${idPub}/count`);
  }
}
