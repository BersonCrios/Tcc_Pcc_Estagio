import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { Observable } from 'rxjs/Observable';
import { EndPoints } from '../utils/api';
import { tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders} from '@angular/common/http';


@Injectable()
export class LocaisService extends BaseService{

  protected headers: HttpHeaders;

  constructor(private http: HttpClient) {
    super();
  }

  //LISTAR LOCAL
  getLocals(): Observable<any> {

    this.headers = new HttpHeaders()
    .set("Accept", "application/json") 
    .set("Authorization", localStorage.getItem('token')); 
  
    return this.http.get(EndPoints.getLocals(),{headers: this.headers}).pipe(
      tap(
        data => {
          return { ...data };
        },
        error => {
          return error;
        }
      )
    );
  }

  //CRIA LOCAL
  createLocals(body): Observable<any> {
    this.headers = new HttpHeaders()
      .set("Accept", "*")
      .set("Access-Control-Allow-Origin", "*")
      .set("Authorization", localStorage.getItem('token'));

    return this.http.post(EndPoints.createLocals(), body, { headers: this.headers }).pipe(
      tap(
        data => {
          return { ...data };
        },
        error => {
          return error;
        }
      )
    );
  }

  //DELETA LOCAL
  deleteLocals(id): Observable<any> {
    this.headers = new HttpHeaders()
      .set("Access-Control-Allow-Origin", "*")
      .set("Authorization", localStorage.getItem('token'));

    return this.http.delete(EndPoints.deleteLocals(id), { headers: this.headers }).pipe(
      tap(
        data => {
          return { ...data };
        },
        error => {
          return error;
        }
      )
    );
  }

}
