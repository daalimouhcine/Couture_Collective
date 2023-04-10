import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/core/services/auth.service';
import { FeaturesService } from 'src/app/core/services/features.service';
import { Client } from 'src/app/features/clients/interfaces/client';
import { Work } from '../../interfaces/work';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-work',
  templateUrl: './add-work.component.html',
  styleUrls: ['./add-work.component.scss'],
})
export class AddWorkComponent implements OnInit {
  work: Work = new Work();

  clients: Client[] = [];
  loading: boolean = false;
  
  addWorkForm = new FormGroup({
    clientId: new FormControl('', [Validators.required]),
    title: new FormControl('', [Validators.required, Validators.minLength(3)]),
    description: new FormControl('', [
      Validators.required,
      Validators.minLength(7),
    ]),
    type: new FormControl('', [Validators.required]),
    keywords: new FormControl('', [
      Validators.required,
      Validators.minLength(5),
    ]),
    deadline: new FormControl(new Date(), [Validators.required]),
    price: new FormControl(0, [Validators.required, Validators.min(1)]),
    showPrice: new FormControl(true, [Validators.required]),
    show_to_public: new FormControl(true, [Validators.required]),
    visibility_code: new FormControl(''),
  });

  constructor(
    private featuresService: FeaturesService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.featuresService
      .getAll(
        `client/byTailor/${this.authService.getUserFromLocalStorage().id}`
      )
      .subscribe((response) => {
        this.clients = response;
      });
  }

  pass_code_change() {
    this.addWorkForm.get('show_to_public')?.value ? this.addWorkForm.get('visibility_code')?.enable : this.addWorkForm.get('visibility_code')?.disable;
  }


  addWork(form: any): void {
    this.work = form.value;
    this.work.tailorId = this.authService.getUserFromLocalStorage().id;
    this.loading = true;
    this.featuresService
      .create(this.work, 'project/create')
      .subscribe((response) => {
        this.loading = false;
          Swal.fire({
            title: response.message,
            icon: response.success ? 'success' : 'error',
            showConfirmButton: false,
            timer: 3000,
          });
          if (response.success) {
            setTimeout(() => {
              this.router.navigate(['/works']);
            }, 3000);
          }
      });
  }
}
