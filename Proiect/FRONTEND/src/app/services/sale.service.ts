import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Sale } from '../model/sale';


@Injectable({ providedIn: 'root' })
export class SaleService {

  private salesUrl = 'http://localhost:8080/sales';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient) { }

  /** GET sales from the server */
  getSales(): Observable<Sale[]> {
    return this.http.get<Sale[]>(this.salesUrl)
      .pipe(
        catchError(this.handleError<Sale[]>('getSales', []))
      );
  }

  /** GET sale by id. Will 404 if id not found */
  getSale(id: number): Observable<Sale> {
    const url = `${this.salesUrl}/${id}`;
    return this.http.get<Sale>(url).pipe(
      catchError(this.handleError<Sale>(`getSale id=${id}`))
    );
  }

  //////// Save methods //////////

  /** POST: add a new sale to the server */
  addSale(sale: Sale): Observable<Sale> {
   // sale.date_sale.setHours(sale.date_sale.getHours()+5);
    return this.http.post<Sale>(this.salesUrl, sale, this.httpOptions).pipe(
      catchError(this.handleError<Sale>('addSale'))
    );
  }

  /** DELETE: delete the sale from the server */
  deleteSale(id: number): Observable<Sale> {
    const url = `${this.salesUrl}/${id}`;
    return this.http.delete<Sale>(url, this.httpOptions).pipe(
      catchError(this.handleError<Sale>('deleteSale'))
    );
  }

  /** PUT: update the Sale on the server */
  updateSale(sale: Sale): Observable<any> {
   // sale.date_sale.setHours(sale.date_sale.getHours()+5);

    return this.http.put(this.salesUrl, sale, this.httpOptions);
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
     // alert(error);

      // TODO: better job of transforming error for user consumption

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}

