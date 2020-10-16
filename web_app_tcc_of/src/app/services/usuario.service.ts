import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { EndPoints } from '../utils/api';
import { tap } from 'rxjs/operators';

@Injectable()
export class UsuarioService extends BaseService{

  protected headers: HttpHeaders;

  constructor(private http: HttpClient) {
    super();
  }

  cadastroUsuario(usuario): Observable<any> {
     return this.http.post(EndPoints.createUser(), usuario).pipe(
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
