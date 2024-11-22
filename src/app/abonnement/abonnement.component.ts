import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { AbonnementServiceService } from '../abonnementService/abonnement-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { identity } from 'rxjs';

@Component({
  selector: 'app-abonnement',
  templateUrl: './abonnement.component.html',
  styleUrls: ['./abonnement.component.scss'],
  providers: [ConfirmationService, MessageService]
})
export class AbonnementComponent implements OnInit {
visibleEdit: boolean=false;
showDialogEdit() {
this.visibleEdit=true;
}
  abonnements: any[] = [];
  visible: boolean = false;
  abonnemenrForm!: FormGroup;
  abonnementId: number=NaN;
  abonnement!:any;

  constructor(
    private abonnementService: AbonnementServiceService,
    private fb: FormBuilder,
    private router: Router,
    private toastrService: ToastrService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService
    ,private activated:ActivatedRoute
  ) {
    this.abonnemenrForm = this.fb.group({
      typeAbonnement: [this.abonnement?.typeAbonnement || ''],
      prixMoi: [this.abonnement?.prixMoi || 0],
      startDate: [this.abonnement?.startDate || new Date()],
      endDate: [this.abonnement?.endDate || new Date()]
    });
  }

  ngOnInit(): void {
    this.getAllAbonnement();
  }

  getAllAbonnement() {
    this.abonnementService.getAllAbonnement().subscribe(res => {
      this.abonnements = res.map((abonnement: any) => {
        return {
          ...abonnement,
          startDate: new Date(abonnement.startDate).toISOString().split('T')[0],
          endDate: new Date(abonnement.endDate).toISOString().split('T')[0]
        };
      });
    });
  }

  addAbonnement() {
    if (this.abonnemenrForm.valid) {
      this.abonnementService.addNewAbonnement(this.abonnemenrForm.value).subscribe(res => {
        this.abonnemenrForm.reset();
        this.toastrService.success('Abonnement ajouté avec succès', 'Succès');
        this.getAllAbonnement();
        this.visible = false;
      });
    } else {
      this.toastrService.warning('Veuillez vérifier les données saisies.', 'Formulaire invalide');
    }
  }

  confirmDelete(idAbonnement :number) {
    this.confirmationService.confirm({
      message: 'Êtes-vous sûr de vouloir supprimer cet abonnement ?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.deleteAbonnement(idAbonnement);
       
      },
      reject: () => {
        this.messageService.add({
          severity: 'info',
          summary: 'Annulé',
          detail: 'Suppression annulée'
        });
      }
    });
  }

  deleteAbonnement(abonnementId: number) {
    this.abonnementService.deleteAbonnement(abonnementId).subscribe({
      
      next: () => {
        this.getAllAbonnement();
        this.toastrService.success('Abonnement supprimé avec succès', 'Succès');
      }
      
    });
  
  }

  public showDialog(): void {
    this.visible = true;
    this.abonnemenrForm.reset(); 
  }
  
  submitForm() {
    if (this.abonnemenrForm.valid) {
     
      const updatedAbonnement = { ...this.abonnement }; 
  
      
      for (let field in this.abonnemenrForm.value) {
        if (this.abonnemenrForm.value[field] !== this.abonnement[field]) {
          updatedAbonnement[field] = this.abonnemenrForm.value[field]; 
        }
      }
  
      
      this.abonnementService.editAbonnement(this.abonnement.idAbonnement, updatedAbonnement).subscribe(
        (response) => {
          console.log('Abonnement updated successfully', response);
          this.visibleEdit = false; 
          this.getAllAbonnement();
        },
        (error) => {
          console.error('Error updating abonnement', error);
        }
      );
    }
  }

  public getAbonnementById(id:number){

    this.showDialogEdit();

    this.abonnementService.getAbonnementById(id).subscribe(res=>{
      const formattedDate = new Date(res.startDate).toISOString().split('T')[0];
      res.startDate = formattedDate;

      const formattedDate2 = new Date(res.endDate).toISOString().split('T')[0];
      res.endDate = formattedDate2;
      
      this.abonnement=res;
    })
  }
  
}
