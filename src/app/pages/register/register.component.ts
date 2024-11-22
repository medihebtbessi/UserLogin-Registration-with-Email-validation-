import { Component } from '@angular/core';
import { RegistrationRequest } from '../../services/models';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  constructor(
    private router:Router,
    private authService:AuthenticationService,private toastrService:ToastrService
  ){}
login() {

  this.router.navigate(['login']);
}
register() {
this.errorMsg=[];
this.authService.register({
  body:this.registerRequest
}).subscribe({
  next:()=>{
    this.router.navigate(['activate-account']);
    this.toastrService.success("sign in with sucess");
  },
  error:(err)=>{
    this.errorMsg=err.error.validationErrors;
    this.toastrService.error("check you data");
  }
})

}
  registerRequest:RegistrationRequest={email:'',firstname:'',
    lastname:'',password:'',role: { roleType: '' }
  }

  errorMsg: Array<string>=[];
}
