import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FeaturesService } from 'src/app/core/services/features.service';
import { Work } from '../../interfaces/work';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Client } from 'src/app/features/clients/interfaces/client';
import { AuthService } from 'src/app/core/services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-work',
  templateUrl: './edit-work.component.html',
  styleUrls: ['./edit-work.component.scss'],
})
export class EditWorkComponent implements OnInit {
  workId: number = 0;

  clients: Client[] = [];
  loading: boolean = false;

  editWorkForm = new FormGroup({
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
    deadline: new FormControl('', [Validators.required]),
    price: new FormControl('', [Validators.required, Validators.min(1)]),
    show_price: new FormControl('', [Validators.required]),
    show_to_public: new FormControl('', [Validators.required]),
    visibility_code: new FormControl(''),
  });

  constructor(
    private featuresService: FeaturesService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id');
    this.featuresService.getBy(id, 'project/byId').subscribe((response) => {
      this.workId = response.id;

      this.editWorkForm = new FormGroup({
        clientId: new FormControl(response.client.id, [Validators.required]),
        title: new FormControl(response.title, [
          Validators.required,
          Validators.minLength(3),
        ]),
        description: new FormControl(response.description, [
          Validators.required,
          Validators.minLength(7),
        ]),
        type: new FormControl(response.type, [Validators.required]),
        keywords: new FormControl(response.keywords, [
          Validators.required,
          Validators.minLength(5),
        ]),
        deadline: new FormControl(response.deadline, [Validators.required]),
        price: new FormControl(response.price, [
          Validators.required,
          Validators.min(1),
        ]),
        show_price: new FormControl(response.show_price, [Validators.required]),
        show_to_public: new FormControl(response.show_to_public, [
          Validators.required,
        ]),
        visibility_code: new FormControl(response.visibility_code),
      });
      console.log(response);
      console.log('form', this.editWorkForm.value);
    });

    this.featuresService
      .getAll(
        `client/byTailor/${this.authService.getUserFromLocalStorage().id}`
      )
      .subscribe((response) => {
        this.clients = response;
      });
  }

  pass_code_change() {
    this.editWorkForm.get('show_to_public')?.value
      ? this.editWorkForm.get('visibility_code')?.enable
      : this.editWorkForm.get('visibility_code')?.disable;
  }

  submitEditWorkForm(form: any) {
    let work: Work = form.value;
    console.log(this.authService.getUserFromLocalStorage().id);
    work.tailorId = this.authService.getUserFromLocalStorage().id;
    this.loading = true;

    this.featuresService
      .update(work, 'project/update/' + this.workId)
      .subscribe((response: any) => {
        this.loading = false;
        if (response.success) {
          Swal.fire({
            title: 'Success!',
            text: 'Work updated successfully',
            icon: 'success',
            confirmButtonText: 'Ok',
          }).then((result) => {
            if (result.isConfirmed) {
              this.router.navigate(['/works']);
            }
          });
        } else {
          Swal.fire({
            title: 'Error!',
            text: response.message,
            icon: 'error',
            confirmButtonText: 'Ok',
          });
        }
      });
  }
}
