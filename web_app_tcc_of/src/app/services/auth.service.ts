import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { EndPoints } from '../utils/api';
import { tap } from "rxjs/operators";
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class AuthService {
  
  protected headers: HttpHeaders;
  constructor(private http: HttpClient) { }

  login(usuario): Observable<any> {
    // this.headers = new HttpHeaders()
    // .set("Access-Control-Allow-Origin", "*")
    // .set("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
     return this.http.post(EndPoints.login(), usuario).pipe(
      tap(
        data => {
          let token = data['token'];
          localStorage.setItem('token', token);
          return { ...data };
        },
        error => {

          return error;
        }
      )
    );
  }
}
