import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ActivateAccountComponent } from './pages/activate-account/activate-account.component';
import { CodeInputModule } from 'angular-code-input';
import { OffreComponent } from './offre/offre.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AddOffreComponent } from './add-offre/add-offre.component';
import { UpdateOffreComponent } from './update-offre/update-offre.component';
import { FilterOffresPipe } from './pipes/SearchOffre';
import {ToastrModule} from 'ngx-toastr';
import { DetailOffreComponent } from './detail-offre/detail-offre.component';
import { AbonnementComponent } from './abonnement/abonnement.component';
import { DialogAbonnementComponent } from './dialog-abonnement/dialog-abonnement.component';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService, MessageService } from 'primeng/api';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ActivateAccountComponent,
    OffreComponent,
    NavbarComponent,
    AddOffreComponent,
    UpdateOffreComponent,
    FilterOffresPipe,
    DetailOffreComponent,
    AbonnementComponent,
    DialogAbonnementComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CodeInputModule,
    ReactiveFormsModule,
    ToastrModule.forRoot({
      progressBar:true,
      closeButton:true,
      newestOnTop:true,
      tapToDismiss:true,
      positionClass:'toast-top-right',
      timeOut:8000
    }),
    DialogModule,
     ButtonModule
     , InputTextModule,
     BrowserAnimationsModule,
     ConfirmDialogModule
       
    
     
    
  

  ],
  providers: [
    provideClientHydration(),
    HttpClient,
    ConfirmationService, MessageService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
