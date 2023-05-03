import { Component, OnInit } from '@angular/core';

import { Sale } from '../../model/sale';
import { SaleService } from '../../services/sale.service';
import { MedicineService } from 'src/app/services/medicine.service';
import { ClientService } from 'src/app/services/client.service';
import { Client } from 'src/app/model/client';
import { Medicine } from 'src/app/model/medicine';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.css']
})
export class SalesComponent implements OnInit {

  selectedMedicine?: Medicine=undefined;
  selectedClient?: Client=undefined;
  sales: Sale[] = [];
  medicines: Medicine[]=[];
  clients: Client[]=[];

  constructor(
    private saleService: SaleService, 
    private medicineService: MedicineService,
    private clientService: ClientService,
    ) 
  { }

  ngOnInit(): void {
    this.getSales();
    this.getClients();
    this.getMedicines();
  }

  getSales(): void {
    this.saleService.getSales()
    .subscribe(sales => {
      console.log(sales);
      this.sales = sales.map(sale => {
        this.medicineService.getMedicine(sale.id_medicament)
        .subscribe(medicine => sale.medicament = medicine);

        return sale;
      }

      );

      return sales;
    });
  }


  getMedicines(): void {
    this.medicineService.getMedicines()
    .subscribe(medicines => {
      this.medicines = medicines
      this.selectedMedicine=medicines[0];
     
    }); 
  }

  getClients(): void {
    this.clientService.getclients()
    .subscribe(clients => {
      this.clients = clients
      this.selectedClient=clients[0];
     
    }); 
  }

  add(dateSale: string, qquantity: string): void {
    dateSale = dateSale.trim();
    let date_sale = new Date(dateSale);
    qquantity = qquantity.trim();
    let quantity=Number(qquantity);


    if (!date_sale || !quantity || !this.selectedClient || !this.selectedMedicine) { return; }
    this.saleService.addSale({ date_sale, quantity, id_client: this.selectedClient.id, 
      id_medicament: this.selectedMedicine.id } as Sale)
      .subscribe(sale => {
        this.getSales();
      });
  }

  delete(sale: Sale): void {
    this.saleService.deleteSale(sale.id).subscribe(sale => {
      this.getSales();
    });
  }

}
