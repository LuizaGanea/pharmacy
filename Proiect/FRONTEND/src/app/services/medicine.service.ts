import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import {Medicine } from '../model/medicine';


@Injectable({ providedIn: 'root' })
export class MedicineService {

  private url = 'http://localhost:8080/medicines';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient) { }

  /** GET clients from the server */
  getMedicines(): Observable<Medicine[]> {
    return this.http.get<Medicine[]>(this.url)
      .pipe(
        catchError(this.handleError<Medicine[]>('getMedicines', []))
      );
  }

 /** GET client by id. Will 404 if id not found */
 getMedicine(id: number): Observable<Medicine> {
    const url = `${this.url}/${id}`;
    return this.http.get<Medicine>(url).pipe(
      catchError(this.handleError<Medicine>(`getMedicine id=${id}`))
    );
  }


  //////// Save methods //////////

  /** POST: add a new client to the server */
  addMedicine(med: Medicine): Observable<Medicine> {
    return this.http.post<Medicine>(this.url, med, this.httpOptions).pipe(
      catchError(this.handleError<Medicine>('addMedicine'))
    );
  }

  /** DELETE: delete the client from the server */
  deleteMedicine(id: number): Observable<Medicine> {
    const url = `${this.url}/${id}`;

    return this.http.delete<Medicine>(url, this.httpOptions).pipe(
      catchError(this.handleError<Medicine>('deleteMedicine'))
    );
  }

  /** PUT: update the client on the server */
  updateMedicine(med: Medicine): Observable<any> {
    return this.http.put(this.url, med, this.httpOptions).pipe(
      catchError(this.handleError<any>('updateMedicine'))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
      //alert(error);

      // TODO: better job of transforming error for user consumption

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}

