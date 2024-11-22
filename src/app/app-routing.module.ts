import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ActivateAccountComponent } from './pages/activate-account/activate-account.component';
import { OffreComponent } from './offre/offre.component';
import { AddOffreComponent } from './add-offre/add-offre.component';
import { UpdateOffreComponent } from './update-offre/update-offre.component';
import { DetailOffreComponent } from './detail-offre/detail-offre.component';
import { AbonnementComponent } from './abonnement/abonnement.component';

const routes: Routes = [
  {
    path:'login',
    component: LoginComponent
  },
  {
    path:'register',
    component: RegisterComponent
  },
  {
    path:'activate-account',
    component: ActivateAccountComponent
  },
  {
    path:'home',
    component: OffreComponent
  },
  {
    path:'offre',
    component: OffreComponent
  },
  {
    path:'add-offre',
    component: AddOffreComponent
  },
  {
    path:'update-offre/:id',
    component: UpdateOffreComponent
  }
  ,{
    path:'detail-offre/:id',
    component: DetailOffreComponent
  }
  ,{
    path:'list-abonnement',
    component: AbonnementComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
