import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Response } from '../interfaces/response';
import { catchError, Observable, of, switchMap } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class FeaturesService {
  constructor(private _http: HttpClient, private router: Router) {}

  getAll(url: string): Observable<any> {
    return this._http.get<any>(environment.baseApi + url).pipe(
      catchError((error) => {
        return of(null);
      }),
      switchMap((response) => {
        return of(response);
      })
    );
  }

  getBy(id: any, url: string): Observable<any> {
    return this._http.get<any>(environment.baseApi + url + '/' + id).pipe(
      catchError((error) => {
        return of(null);
      }),
      switchMap((response) => {
        return of(response);
      })
    );
  }

  create(data: any, url: string): Observable<Response> {
    return this._http.post<any>(environment.baseApi + url, data).pipe(
      catchError((error) => {
        return of(false);
      }),
      switchMap((response) => {
        return of(response);
      })
    );
  }

  update(data: any, url: string): Observable<Response> {
    return this._http.put<any>(environment.baseApi + url, data).pipe(
      catchError((error) => {
        return of(false);
      }),
      switchMap((response) => {
        return of(response);
      })
    );
  }

  delete(data: any, url: string): Observable<Response> {
    return this._http.delete<any>(environment.baseApi + url).pipe(
      catchError((error) => {
        return of(false);
      }),
      switchMap((response) => {
        return of(response);
      })
    );
  }
}
