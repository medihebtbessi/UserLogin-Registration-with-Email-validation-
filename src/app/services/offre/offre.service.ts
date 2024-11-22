import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Image } from '../models/Image';

@Injectable({
  providedIn: 'root'
})
export class OffreService {

  constructor(private http:HttpClient) { }


  public getAllOffres():Observable<any>{
    return this.http.get("http://localhost:8090/api/v1/offre/getAllOffre");
  }

  public addOffre(offre :any):Observable<any>{
    return this.http.post("http://localhost:8090/api/v1/offre/addOffre",offre);
  }

  public updateOffre(id:number ,offreUp:any):Observable<any>{
    console.log(offreUp);
    return this.http.put(`http://localhost:8090/api/v1/offre/updateOffre/`+id,offreUp);

  }

  public getOffreById(id:number):Observable<any>{
    return this.http.get(`http://localhost:8090/api/v1/offre/getOffre/`+id)
  }

  public deleteOffre(id:number): Observable<any>{
    return this.http.delete(`http://localhost:8090/api/v1/offre/delete/`+id);
  }

  uploadImageProd(file:File,filename:string,idOffre:number):Observable<any>{
    const imageFormData = new FormData();
    imageFormData.append('image', file, filename);
    const url ="http://localhost:8090/api/v1/image/uploadImageOffre/"+idOffre ;
    return this.http.post(url, imageFormData);

  }
  loadImage(id: number): Observable<Image> {
    const url = "http://localhost:8090/api/v1/image/getImagesProd/"+id;
    return this.http.get<Image>(url);
    }

    
   

 

  // Capture the file on selection
  




}
