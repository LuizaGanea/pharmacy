import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import {Client } from '../model/client';


@Injectable({ providedIn: 'root' })
export class ClientService {

  private clientsUrl = 'http://localhost:8080/clients';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient) { }

  /** GET clients from the server */
  getclients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.clientsUrl)
      .pipe(
        catchError(this.handleError<Client[]>('getclients', []))
      );
  }

  /** GET client by id. Will 404 if id not found */
  getclient(id: number): Observable<Client> {
    const url = `${this.clientsUrl}/${id}`;
    return this.http.get<Client>(url).pipe(
      catchError(this.handleError<Client>(`getclient id=${id}`))
    );
  }

  //////// Save methods //////////

  /** POST: add a new client to the server */
  addclient(client: Client): Observable<Client> {
    //client.dateOfBirth.setHours(client.dateOfBirth.getHours()+5);
    return this.http.post<Client>(this.clientsUrl, client, this.httpOptions).pipe(
      catchError(this.handleError<Client>('addclient'))
    );
  }

  /** DELETE: delete the client from the server */
  deleteclient(id: number): Observable<Client> {
    const url = `${this.clientsUrl}/${id}`;

    return this.http.delete<Client>(url, this.httpOptions).pipe(
      catchError(this.handleError<Client>('deleteclient'))
    );
  }

  /** PUT: update the client on the server */
  updateclient(client: Client): Observable<any> {
    //client.dateOfBirth.setHours(client.dateOfBirth.getHours()+5);

    return this.http.put(this.clientsUrl, client, this.httpOptions).pipe(
      catchError(this.handleError<any>('updateclient'))
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

