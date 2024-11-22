import { Component } from '@angular/core';
import { DialogModule } from 'primeng/dialog';

@Component({
  selector: 'app-dialog-abonnement',
  templateUrl: './dialog-abonnement.component.html',
  styleUrl: './dialog-abonnement.component.scss',

})
export class DialogAbonnementComponent {

  visible: boolean = false;

  showDialog() {
      this.visible = true;
  }

}
