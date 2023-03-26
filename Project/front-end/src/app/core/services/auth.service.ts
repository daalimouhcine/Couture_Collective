import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, of, switchMap } from 'rxjs';
import { CurrentUser, User } from 'src/app/features/users/interfaces/users';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  authenticated: boolean = false;
  currentUser: CurrentUser = new CurrentUser();

  constructor(private _http: HttpClient, private router: Router) { }

  public getUserFromLocalStorage(): CurrentUser {
    const user = localStorage.getItem('currentUser');
    return user != null ? JSON.parse(user) : null;
  }

  public saveUserToLocalStorage(user: CurrentUser): void {
    localStorage.setItem('currentUser', JSON.stringify(user));
  }

  public loadToken(): string {
    const currentToken = localStorage.getItem("token");
    return currentToken != null ? currentToken : "";
  }

  /**
* Setter & getter for access token
*/

  set token(token: string) {
    localStorage.setItem("token", token);
  }

  get token(): string {
    return localStorage.getItem("token") ?? '';
  }

  signIn(credentials: User): Observable<any> {
    return this._http.post<any>(environment.baseApi + 'auth/login', credentials).pipe(
      catchError((error) => {
        return of(null)
      }),
      switchMap((response) => {
        if(response != null) {
          this.token = response.token;
          this.authenticated = true;
          this.saveUserToLocalStorage(response.tailorResponse);
        }
        return of(response);
      })
    )
  }

  signUp(user: User): Observable<any> {
    return this._http.post<any>(environment.baseApi + 'auth/register', user).pipe(
      catchError((error) => {
        return of(null)
      }),
      switchMap((response) => {
        return of(response);
      }
    ))
  }

  checkAuth(): Observable<boolean> {
    if(this.token) {
      return this._http.post<any>(environment.baseApi + 'auth/check-token', this.token).pipe(
        catchError(error => {
          return of(false)
        }),
        switchMap((response) => {
          return of(response)
        }))
    } else {
      return of(false);
    }
  }

  signOut(): void {
    localStorage.clear();
    this.router.navigate(['/auth/login']);
    window.location.reload();
  }

  logoutWithGuard(): void {
    localStorage.clear();
    this.router.navigate(['/auth/login']);
  }

}
