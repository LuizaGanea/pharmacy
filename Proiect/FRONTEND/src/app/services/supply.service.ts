import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Supply } from '../model/supply';


@Injectable({ providedIn: 'root' })
export class SupplyService {

  private suppliesUrl = 'http://localhost:8080/supplies';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient) { }

  /** GET supplies from the server */
  getSupplies(): Observable<Supply[]> {
    return this.http.get<Supply[]>(this.suppliesUrl)
      .pipe(
        catchError(this.handleError<Supply[]>('getSupplies', []))
      );
  }

  /** GET supply by id. Will 404 if id not found */
  getSupply(id: number): Observable<Supply> {
    const url = `${this.suppliesUrl}/${id}`;
    return this.http.get<Supply>(url).pipe(
      catchError(this.handleError<Supply>(`getSupplies id=${id}`))
    );
  }

  //////// Save methods //////////

  /** POST: add a new supply to the server */
  addSupply(supply: Supply): Observable<Supply> {
    //supply.date_supply.setHours(supply.date_supply.getHours()+5);

    return this.http.post<Supply>(this.suppliesUrl, supply, this.httpOptions).pipe(
      catchError(this.handleError<Supply>('addSupply'))
    );
  }

  /** DELETE: delete the supply from the server */
  deleteSupply(id: number): Observable<Supply> {
    const url = `${this.suppliesUrl}/${id}`;

    return this.http.delete<Supply>(url, this.httpOptions).pipe(
      catchError(this.handleError<Supply>('deleteSupply'))
    );
  }

  /** PUT: update the Supply on the server */
  updateSupply(supply: Supply): Observable<any> {
    //supply.date_supply.setHours(supply.date_supply.getHours()+5);
    return this.http.put(this.suppliesUrl, supply, this.httpOptions).pipe(
      catchError(this.handleError<any>('updateSupply'))
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

      // TODO: better job of transforming error for user consumption

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}

