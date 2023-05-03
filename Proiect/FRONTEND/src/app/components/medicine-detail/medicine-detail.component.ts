import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Medicine } from 'src/app/model/medicine';
import { MedicineService } from 'src/app/services/medicine.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-medicine-detail',
  templateUrl: './medicine-detail.component.html',
  styleUrls: ['./medicine-detail.component.css']
})
export class MedicineDetailComponent {
  medicine: Medicine| undefined;

  constructor(
    private route: ActivatedRoute,
    private medicineService: MedicineService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getMedicine();
  }

  getMedicine(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.medicineService.getMedicine(id)
      .subscribe(medicine => this.medicine = medicine);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.medicine) {
      this.medicineService.updateMedicine(this.medicine)
        .subscribe(() => this.goBack());
    }
  }

}
