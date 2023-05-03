import { Component, OnInit } from '@angular/core';
import { MedicineService } from 'src/app/services/medicine.service';

import { Medicine } from '../../model/medicine';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-medicine',
  templateUrl: './medicine.component.html',
  styleUrls: ['./medicine.component.css']
})
export class MedicineComponent implements OnInit {
  medicines: Medicine[] = [];

  constructor(private medicineService: MedicineService) { }

  ngOnInit(): void {
    this.getMedicines();
  }

  getMedicines(): void {
    this.medicineService.getMedicines()
    .subscribe(medicines => this.medicines = medicines);
  }

  add(name: string, description: string): void {
    name = name.trim();
    description = description.trim();
  

    if (!name || !description) { return; }
    this.medicineService.addMedicine({ 
      name: name,
      description: description,
      requiresPrescription: false,
      minAge: 18 
    } as Medicine)
      .subscribe(med => {
        this.getMedicines();
      });
  }

  delete(med: Medicine): void {
    this.medicineService.deleteMedicine(med.id).subscribe(med => {
      this.getMedicines();
    });
  }

}

