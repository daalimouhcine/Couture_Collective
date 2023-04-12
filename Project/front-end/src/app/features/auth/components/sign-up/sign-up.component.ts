import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { User } from 'src/app/features/users/interfaces/users';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss'],
})
export class SignUpComponent implements OnInit {
  user: User = new User();
  loading: boolean = false;
  alertMessage: string = '';
  alertColor: string = '';

  registerForm = new FormGroup({
    name: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
    ]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(6),
    ]),
  });

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {}

  register(form: any): void {
    this.user = form.value;
    if (this.registerForm.valid) {
      this.loading = true;
      this.authService.signUp(this.user).subscribe((response) => {
        this.loading = false;
        this.alertMessage = response.message;
        this.alertColor = response.success ? 'success' : 'danger';
        if (response.success) {
          Swal.fire({
            position: "center",
            icon: "success",
            title: response.data.status,
            showConfirmButton: false,
            timer: 1500,
          }).then(() => {
            this.router.navigate(['/auth/login']);
          });
        } else {
          Swal.fire({
            position: "center",
            icon: "error",
            title: response.message,
            showConfirmButton: false,
            timer: 1500,
          })
        }
      });
    }
  }
}
