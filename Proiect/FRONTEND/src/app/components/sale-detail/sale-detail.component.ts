import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Sale } from '../../model/sale';
import { SaleService } from '../../services/sale.service';
import { Medicine } from 'src/app/model/medicine';
import { Client } from 'src/app/model/client';
import { MedicineService } from 'src/app/services/medicine.service';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-sale-detail',
  templateUrl: './sale-detail.component.html',
  styleUrls: [ './sale-detail.component.css' ]
})
export class SaleDetailComponent implements OnInit {
  selectedMedicine?: Medicine=undefined;
  selectedClient?: Client=undefined;
  sale: Sale | undefined;
  medicines: Medicine[]=[];
  clients: Client[]=[];

  constructor(
    private route: ActivatedRoute,
    private saleService: SaleService,
    private medicineService: MedicineService,
    private clientService: ClientService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getSale();
  
  }

  getSale(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.saleService.getSale(id)
      .subscribe(sale => { this.sale = sale
       this.getClients();
       this.getMedicines();
      });
  }

  getMedicines(): void {
    this.medicineService.getMedicines()
    .subscribe(medicines => {
      this.medicines = medicines
      this.selectedMedicine=medicines.find((medicine) => medicine.id == this.sale?.id_medicament)
     
    }); 
  }

  getClients(): void {
    this.clientService.getclients()
    .subscribe(clients => {
      this.clients = clients
      this.selectedClient=clients.find((client) => client.id == this.sale?.id_client)
     
    }); 
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.sale) {
      this.saleService.updateSale(this.sale)
        .subscribe(() => this.goBack());
    }
  }
}
