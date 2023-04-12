import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';
import { CurrentUser } from 'src/app/features/users/interfaces/users';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  user: CurrentUser = new CurrentUser();
  profileOpen: boolean = false;

  constructor(private authService: AuthService) {};
  ngOnInit(): void {
    this.user = this.authService.getUserFromLocalStorage();
  }

  logout() {
    this.authService.logoutWithGuard();
    this.profileOpen = false;
  }
}
