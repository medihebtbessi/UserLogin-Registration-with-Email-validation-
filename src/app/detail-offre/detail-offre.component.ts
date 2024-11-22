import { Component, OnInit } from '@angular/core';
import { OffreService } from '../services/offre/offre.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Offre } from '../modules/Offre';

@Component({
  selector: 'app-detail-offre',
  templateUrl: './detail-offre.component.html',
  styleUrl: './detail-offre.component.scss'
})
export class DetailOffreComponent implements OnInit{
  id!:number;
  offre!:any;
  public constructor(private offreService:OffreService, private router:Router,private activated:ActivatedRoute){}
  ngOnInit(): void {
    this.id=+this.activated.snapshot.params['id'];
    this.getOffreById();
    
  }



  public getOffreById(){
    this.offreService.getOffreById(this.id).subscribe(res=>{
      const formattedDate = new Date(res.dateDebut).toISOString().split('T')[0];
      res.dateDebut = formattedDate;

      const formattedDate2 = new Date(res.dateFin).toISOString().split('T')[0];
      res.dateFin = formattedDate;
      this.offre=res;
      
    });
    
  }



}
