import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';



@Injectable({ providedIn: 'root' })
export class TransactionService {

  private url = 'http://localhost:8080/';  // URL to web api


  constructor(
    private http: HttpClient) { }

 
  //////// Save methods //////////

 
  save() {
    return this.http.post(this.url+"save",null);
  }

  restore() {
   return this.http.post(this.url+"restore",null);
  }




  
}

