import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { User } from 'src/app/features/users/interfaces/users';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss'],
})
export class SignInComponent implements OnInit {
  user: User = new User();
  loading: boolean = false;
  alertMessage: string = '';
  alertColor: string = '';

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {}

  login(form: any): void {
    this.user = form.value;
    if (this.loginForm.valid) {
      localStorage.clear();
      this.loading = true;
      this.alertMessage = '';
      this.alertColor = '';
      this.authService.signIn(this.user).subscribe((response) => {
        this.loading = false;
        this.alertMessage = response.message;
        this.alertColor = response.success ? 'success' : 'danger';
        if (response.success) {
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: this.alertMessage,
            showConfirmButton: false,
            timer: 1500,
          }).then(() => {
            this.router.navigate(['/works']);
          });
        } else {
          Swal.fire({
            position: 'center',
            icon: 'error',
            title: response.message,
            showConfirmButton: false,
            timer: 1500,
          });
        }
      });
    } else {
      this.alertMessage = 'All information required';
      this.alertColor = 'danger';
    }
  }
}
