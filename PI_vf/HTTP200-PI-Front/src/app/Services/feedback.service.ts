import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  newProfile: any;


  constructor(private http: HttpClient) { } 
  private endpoint = 'http://localhost:8080/api';

getFeedbacks(): Observable<any> {
  const url = `${this.endpoint}/Feedbacks`;
  return this.http.get(url);
}


addFeedback(Feedback: any): Observable<any> {
  const url = `${this.endpoint}/addFeedback`;
  return this.http.post(url, Feedback);
}

updateFeedback(feedback: any): Observable<any> {
 const body = this.removeCircularReferences(feedback);
  const url = `${this.endpoint}/updateFeedback`;
  return this.http.put(url,body);
}

deleteFeedback(id: number): Observable<any> {
  const url = `${this.endpoint}/deleteFeedback/${id}`;
  return this.http.delete(url);
  }
  getFeedbacksByProfileUsername(username: string):Observable<any>{
    const url = `${this.endpoint}/feedbacks/byProfile/${username}`;
    return this.http.get(url);
  } 
  FeedbackAnalysis(username: string): Observable<Blob> {
    const url = `${this.endpoint}/feedbacks/sentiment/${username}`;
    return this.http.get(url, { responseType: 'blob' });
  }
  
  removeCircularReferences(obj: any): any {
    const seenObjects = new WeakMap();
  
    function replacer(key: string, value: any) {
      if (typeof value === 'object' && value !== null) {
        if (seenObjects.has(value)) {
          return;
        }
        seenObjects.set(value, true);
      }
      return value;
    }
  
    return JSON.parse(JSON.stringify(obj, replacer));
  }  


}

