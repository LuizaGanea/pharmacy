import { Component, OnInit } from '@angular/core';
import { Medicine } from 'src/app/model/medicine';
import { MedicineService } from 'src/app/services/medicine.service';

import { Supply } from '../../model/supply';
import { SupplyService } from '../../services/supply.service';

@Component({
  selector: 'app-supplies',
  templateUrl: './supplies.component.html',
  styleUrls: ['./supplies.component.css']
})
export class SuppliesComponent implements OnInit {
  supplies: Supply[] = [];
  medicines: Medicine[]=[];
  selectedMedicine?: Medicine=undefined;

  constructor(
    private supplyService: SupplyService, 
    private medicineService: MedicineService
  ) { }

  ngOnInit(): void {
    this.getSupplies();
    this.getMedicines();
  }

  getSupplies(): void {
    this.supplyService.getSupplies()
    .subscribe(supplies => {
      this.supplies = supplies.map(supply => {
        this.medicineService.getMedicine(supply.id_medicament)
        .subscribe(medicine => supply.medicament = medicine);

        return supply;
      }

      );

      return supplies;
    });
  }

  getMedicines(): void {
    this.medicineService.getMedicines()
    .subscribe(medicines => {
      this.medicines = medicines
      this.selectedMedicine=medicines[0];
    }); 
  }

  add(dateSupply: string, qquantity: string): void {
    dateSupply = dateSupply.trim();
    let date_supply = new Date(dateSupply);
    qquantity = qquantity.trim();
    let quantity=Number(qquantity);

    if (!date_supply || !quantity || !this.selectedMedicine) { return; }
    this.supplyService.addSupply({ date_supply, quantity, id_medicament: this.selectedMedicine?.id} as Supply)
      .subscribe(supply => {
        this.getSupplies();
      });
  }

  delete(supply: Supply): void {
    this.supplyService.deleteSupply(supply.id).subscribe(supply => {
      this.getSupplies();
    });
  }

}
