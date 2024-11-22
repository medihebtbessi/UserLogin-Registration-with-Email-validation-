import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OffreService } from '../services/offre/offre.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-update-offre',
  templateUrl: './update-offre.component.html',
  styleUrl: './update-offre.component.scss'
})
export class UpdateOffreComponent implements OnInit{

  offreForm!: FormGroup;
  photos: Array<Blob> = []; 
  id!:number;

  constructor(private fb: FormBuilder,private offreService:OffreService, private router:Router,private activated:ActivatedRoute,private toastrService:ToastrService) {
   
  }
  ngOnInit(): void {
   this.id=+this.activated.snapshot.params['id'];
   console.log(this.id);
   this.offreForm = this.fb.group({
    nom: ['', Validators.required],
    prix: ['', [Validators.required, Validators.min(0)]],
    address: ['', Validators.required],
    description: ['', Validators.required],
    dateDebut: ['', Validators.required],
    dateFin: ['', Validators.required],
    photos: ['']
  });
  this.getOffreById();
 
  }

  public getOffreById(){
    
    this.offreService.getOffreById(this.id).subscribe(res=>{
      const formattedDate = new Date(res.dateDebut).toISOString().split('T')[0];
      res.dateDebut = formattedDate;

      const formattedDate2 = new Date(res.dateFin).toISOString().split('T')[0];
      res.dateFin = formattedDate2;
      this.offreForm.patchValue(res);
    })
  }
  submitForm(){
      this.offreService.updateOffre(this.id,this.offreForm.value).subscribe(()=>{
        
        this.router.navigateByUrl("/offre");
        this.toastrService.success("sign in with sucess");
      })
    }


}
