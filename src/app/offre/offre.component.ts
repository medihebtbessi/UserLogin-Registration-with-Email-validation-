import { Component, Input, OnInit } from '@angular/core';
import { OffreService } from '../services/offre/offre.service';
import { Router } from '@angular/router';
import { Offre } from '../modules/Offre';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-offre',
  templateUrl: './offre.component.html',
  styleUrl: './offre.component.scss',
  
})
export class OffreComponent implements OnInit{

   searchAdd:string='';
  offres:Offre[]=[];
    
  public constructor(private offreService:OffreService,private router:Router,private toastrService:ToastrService,private http:HttpClient){
    
   
  }
  ngOnInit(): void {
    this.getAllOffres();
    
  }

  public getAllOffres(){
    this.offreService.getAllOffres().subscribe(res=>{
      this.offres=res;
      
    });
  }

  confirmDelete(idOffre: number): void {
    const confirmed = window.confirm('Are you sure you want to delete this offer?');
    if (confirmed) {
      this.deleteOffre(idOffre);
      this.getAllOffres();
    }
  }

  public deleteOffre(id: number): void {
    this.offreService.deleteOffre(id).subscribe(() => {
     
    });
  }

  selectedFile: File | null = null;

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
    console.log(this.selectedFile?.name); 
  }

  importOffre(): void {
    if (!this.selectedFile) {
      this.toastrService.error("Please select a file before importing.");
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile);

    if(formData){
      this.http.post('http://localhost:8090/api/v1/offre/importOffre', formData).subscribe(()=>{
        
      } );
      this.toastrService.success("Done");
    }
    
  }


}
