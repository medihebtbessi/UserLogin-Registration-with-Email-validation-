import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AbonnementServiceService {

  constructor(private http:HttpClient) { }

  public getAllAbonnement():Observable<any>{
    return this.http.get('http://localhost:8090/api/v1/abonnement/getAllAbonnement');
  }

  public addNewAbonnement(abonnement: any): Observable<any> {
    const token = localStorage.getItem('token'); 
    console.log(token);
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`, 
      'Content-Type': 'application/json'  
    });
  
    return this.http.post(
      'http://localhost:8090/api/v1/abonnement/addAbonnement',
      abonnement,
      { headers }
    );
  }

  public deleteAbonnement(id:number):Observable<any>{
    return this.http.delete('http://localhost:8090/api/v1/abonnement/deleteAbonnement/'+id);
    
  }

  public editAbonnement(id:number,abonnement:any):Observable<any>{
    return this.http.put('http://localhost:8090/api/v1/abonnement/updateAbonnement/'+id,abonnement);
    console.log(abonnement);
  }

  public getAbonnementById(id:number):Observable<any>{
    return this.http.get('http://localhost:8090/api/v1/abonnement/getAbonnementById/'+id);
  }
}
