import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { User } from 'src/app/features/users/interfaces/users';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit{
  user: User = new User();
  loading: boolean = false;
  registerForm = new FormGroup({
    fullName: new FormControl('', [Validators.required, Validators.minLength(3)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
  })

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  register(form: any): void {
    this.user = form.value; 
    if(this.registerForm.valid) {
      this.loading = true;
      this.authService.signUp(this.user).subscribe((response) => {
        this.loading = false;
        this.router.navigate(['/auth/signin']);
      }

    }
  }


}
