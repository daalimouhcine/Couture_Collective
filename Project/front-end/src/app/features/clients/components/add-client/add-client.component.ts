import { Component, OnInit } from '@angular/core';
import { Client } from '../../interfaces/client';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FeaturesService } from 'src/app/core/services/features.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.scss'],
})
export class AddClientComponent implements OnInit {
  client: Client = new Client();
  loading: boolean = false;

  addClientForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    gender: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    phone: new FormControl('', [Validators.required]),
    measuringUnit: new FormControl('', [Validators.required]),
    height: new FormControl(0, [Validators.required]),
    armLength: new FormControl(0, [Validators.required]),
    legLength: new FormControl(0, [Validators.required]),
    waist: new FormControl(0, [Validators.required]),
    chest: new FormControl(0, [Validators.required]),
    hips: new FormControl(0, [Validators.required]),
    neck: new FormControl(0, [Validators.required]),
    shoulder: new FormControl(0, [Validators.required]),
    biceps: new FormControl(0, [Validators.required]),
  });

  constructor(private features: FeaturesService, private router: Router) {}

  ngOnInit() {}

  addClient(form: any): void {
    this.client = form.value;
    if (this.addClientForm.valid) {
      this.loading = true;
      this.features
        .create(this.client, 'client/create')
        .subscribe((response) => {
          this.loading = false;
          Swal.fire({
            title: response.message,
            icon: response.success ? 'success' : 'error',
            showConfirmButton: false,
            timer: 2000,
          });
          if (response.success) {
            setTimeout(() => {
              this.router.navigate(['/clients']);
            }, 2000);
          }
        });
    }
  }
}
