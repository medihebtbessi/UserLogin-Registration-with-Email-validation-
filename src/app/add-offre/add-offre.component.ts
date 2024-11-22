import { Component, OnInit } from '@angular/core';
import { OffreService } from '../services/offre/offre.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Image } from '../services/models/Image';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-offre',
  templateUrl: './add-offre.component.html',
  styleUrls: ['./add-offre.component.scss'] // Corrected to styleUrls
})
export class AddOffreComponent implements OnInit {
  offreForm: FormGroup;
  photos: Array<Blob> = []; 
  uploadedImage!: File;
  imagePath: any;

  constructor(private fb: FormBuilder, private offreService: OffreService, private router: Router,private toastrService:ToastrService) {
   
    this.offreForm = this.fb.group({
      nom: ['', Validators.required],
      prix: ['', [Validators.required, Validators.min(0)]],
      address: ['', Validators.required],
      description: ['', Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      imageUrl: [''] 
    });
  }

  ngOnInit(): void {}

  addOffre() {
    if (this.offreForm.valid) {
     
      //this.offreService.uploadImage(this.uploadedImage, this.uploadedImage.name,).subscribe((img: Image) => {
        
        //this.offreForm.patchValue({ imageUrl: img.image }); 

        
        this.offreService.addOffre(this.offreForm.value).subscribe(res => {
          this.offreForm.reset();
          this.router.navigateByUrl("/offre");
          this.toastrService.success("votre offre est ajouter avec succes","added with sucess")
        });
    
    }else{
      this.toastrService.error("check your data","check");
    }
  }

  onImageUpload(event: any) {
    this.uploadedImage = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(this.uploadedImage);
    reader.onload = (_event) => {
      this.imagePath = reader.result;
    };
  }
}
