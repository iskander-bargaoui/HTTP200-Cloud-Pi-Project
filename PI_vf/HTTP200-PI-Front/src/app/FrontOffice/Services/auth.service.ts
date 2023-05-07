import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/app/Models/user';

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {


  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signin',
      {
        username,
        password,
      },
      httpOptions
    );
  }

  register(username: string, nom:string, prenom:string, email: string, phoneNumber:number,
    birthDate:Date, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signup',
      {
        username,
        nom,
        prenom,
        email,
        phoneNumber,
        birthDate,
        password,
      },
      httpOptions
    );
  }

  logout(): Observable<any> {
    localStorage.clear();

    return this.http.post(AUTH_API + 'signout', { }, httpOptions);
  }

 //Cruds
 public getAllUsers(): Observable<any>{
  return this.http.get(`${AUTH_API}allUsers`)
}

editUser(user: any){
  return this.http.put(`${AUTH_API}updateUser/`,user)
}
deleteUser(idUser: any){
  return this.http.delete(`${AUTH_API}delUser/${idUser}`)
}

public addUser(user :User):Observable<any>{
  return this.http.post<any>("http://localhost:8080/api/auth/addUser",user)

}
public lockUser(user: any):Observable<any>{
  return this.http.put(`${AUTH_API}/activateUser/`,user)

} 
updatePassword(email: string, password: string) {
  return this.http.put('http://localhost:8080/api/auth/updatepassword/' + email + '/' + password  + '/' + password , { responseType: 'text' });
}
forgotPassword(email: string) {
  return this.http.get('http://localhost:8080/api/auth/sendme/' + email);
}
enableUser(id: number){
  return this.http.put(`${AUTH_API}enable/${id}`,null)
}
disableUser(id: number){
  return this.http.put(`${AUTH_API}disable/${id}`,null)
}
}