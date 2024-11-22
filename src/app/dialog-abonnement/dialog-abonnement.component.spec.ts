import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAbonnementComponent } from './dialog-abonnement.component';

describe('DialogAbonnementComponent', () => {
  let component: DialogAbonnementComponent;
  let fixture: ComponentFixture<DialogAbonnementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DialogAbonnementComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DialogAbonnementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
