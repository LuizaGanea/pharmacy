import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Supply } from '../../model/supply';
import { SupplyService } from '../../services/supply.service';
import { Medicine } from 'src/app/model/medicine';
import { MedicineService } from 'src/app/services/medicine.service';

@Component({
  selector: 'app-supply-detail',
  templateUrl: './supply-detail.component.html',
  styleUrls: [ './supply-detail.component.css' ]
})
export class SupplyDetailComponent implements OnInit {
  supply: Supply | undefined;
  medicines: Medicine[]=[];
  selectedMedicine?: Medicine | undefined;

  constructor(
    private route: ActivatedRoute,
    private supplyService: SupplyService,
    private medicineService: MedicineService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getSupply();
    
  }
  getMedicines(): void {
    this.medicineService.getMedicines()
    .subscribe(medicines => {
      this.medicines = medicines
      this.selectedMedicine=medicines.find((medicine) => medicine.id == this.supply?.id_medicament)
    }); 
  }

  getSupply(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.supplyService.getSupply(id)
      .subscribe(supply => {
        this.supply = supply
        this.getMedicines();
      });

      

  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.supply) {
      this.supplyService.updateSupply(this.supply)
        .subscribe(() => this.goBack());
    }
  }
}
