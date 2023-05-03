import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Client } from '../../model/client';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-client-detail',
  templateUrl: './client-detail.component.html',
  styleUrls: [ './client-detail.component.css' ]
})
export class ClientDetailComponent implements OnInit {
  client: Client | undefined;

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getclient();
  }

  getclient(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.clientService.getclient(id)
      .subscribe(client => this.client = client);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.client) {
      this.clientService.updateclient(this.client)
        .subscribe(() => this.goBack());
    }
  }
}
