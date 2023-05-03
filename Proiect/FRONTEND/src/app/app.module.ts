import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { ClientDetailComponent } from './components/client-detail/client-detail.component';
import { ClientsComponent } from './components/clients/clients.component';
import { MedicineDetailComponent } from './components/medicine-detail/medicine-detail.component';
import { MedicineComponent } from './components/medicine/medicine.component';
import { SalesComponent } from './components/sales/sales.component';
import { SuppliesComponent } from './components/supplies/supplies.component';
import { SupplyDetailComponent } from './components/supply-detail/supply-detail.component';
import { SaleDetailComponent } from './components/sale-detail/sale-detail.component';
import { ErrorCatchingInterceptor } from './interceptor/error-catching-interceptor';

@NgModule({
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorCatchingInterceptor,
      multi: true
   }
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  declarations: [
    AppComponent,
    ClientsComponent,
    ClientDetailComponent,
    MedicineDetailComponent,
    MedicineComponent,
    SalesComponent,
    SuppliesComponent,
    SupplyDetailComponent,
    SaleDetailComponent,
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/