import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { first } from 'rxjs';
import { TransactionService } from './services/transaction.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Pharmacy';

  constructor(private transactionService: TransactionService, private router: Router,
     private location: Location){}

  save(){ 
    this.transactionService.save().pipe(first())
    .subscribe({
      next: () => {
        console.log("success");
    
      location.reload();
      },
      error: (error) => {
        console.log("error");
        location.reload();
      },
    });

  }


  restore() { 
    this.transactionService.restore().pipe(first())
    .subscribe({
      next: () => {
        console.log("success");
      location.reload();
        
      },
      error: (error) => {
        console.log("error");
        location.reload();
      },
    });

  }

}



/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/