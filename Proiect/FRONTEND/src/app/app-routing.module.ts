import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ClientsComponent } from './components/clients/clients.component';
import { ClientDetailComponent } from './components/client-detail/client-detail.component';
import { MedicineComponent } from './components/medicine/medicine.component';
import { MedicineDetailComponent } from './components/medicine-detail/medicine-detail.component';
import { SuppliesComponent } from './components/supplies/supplies.component';
import { SupplyDetailComponent } from './components/supply-detail/supply-detail.component';
import { SalesComponent } from './components/sales/sales.component';
import { SaleDetailComponent } from './components/sale-detail/sale-detail.component';

const routes: Routes = [
  { path: '', redirectTo: '/clients', pathMatch: 'full' },
  { path: 'clients', component: ClientsComponent },
  { path: 'clients/:id/details', component: ClientDetailComponent },
  { path: 'medicine', component: MedicineComponent },
  { path: 'medicine/:id/details', component: MedicineDetailComponent },
  { path: 'supplies', component: SuppliesComponent },
  { path: 'supplies/:id/details', component: SupplyDetailComponent },
  { path: 'sales', component: SalesComponent },
  { path: 'sales/:id/details', component: SaleDetailComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/