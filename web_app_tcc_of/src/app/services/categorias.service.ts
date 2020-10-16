import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { BaseService } from './base.service';
import { Observable } from 'rxjs/Observable';
import { EndPoints } from '../utils/api';
import { tap } from 'rxjs/operators';

@Injectable()
export class CategoriasService extends BaseService {

  protected headers: HttpHeaders;

  constructor(private http: HttpClient) {
    super();
  }

  //LISTA CATEGORIAS
  getCategories(): Observable<any> {
    this.headers = new HttpHeaders()
      .set("Accept", "application/json")
      .set("Authorization", localStorage.getItem('token'));

    return this.http.get(EndPoints.getCategories(), { headers: this.headers }).pipe(
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

  //CRIA CATEGORIAS
  createCategories(body): Observable<any> {
    this.headers = new HttpHeaders()
      .set("Accept", "application/json")
      .set("Authorization", localStorage.getItem('token'));

    return this.http.post(EndPoints.createCategories(), body, { headers: this.headers }).pipe(
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

  //DELETA CATEGORIAS
  deleteCategories(id): Observable<any> {
    this.headers = new HttpHeaders()
      .set("Access-Control-Allow-Origin", "*")
      .set("Authorization", localStorage.getItem('token'));

    return this.http.delete(EndPoints.deleteCategories(id), { headers: this.headers }).pipe(
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

  //EDITAR CATEGORIAS
  editCategories(id, body): Observable<any> {
    this.headers = new HttpHeaders()
      .set("Accept", "application/json")
      .set("Authorization", localStorage.getItem('token'));

    return this.http.put(EndPoints.editCategories(id), body, { headers: this.headers }).pipe(
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
