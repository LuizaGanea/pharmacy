import { Component, OnInit } from '@angular/core';

import { Client } from '../../model/client';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {
  clients: Client[] = [];

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.getclients();
  }

  getclients(): void {
    this.clientService.getclients()
    .subscribe(clients => this.clients = clients);
  }

  add(firstName: string, lastName: string, cnp: string, dob: string): void {
    firstName = firstName.trim();
    lastName = lastName.trim();
    cnp = cnp.trim();
    dob = dob.trim();
    let dateOfBirth = new Date(dob);

    if (!firstName || !lastName || !cnp || !dob) { return; }
    this.clientService.addclient({ firstName,lastName, cnp, dateOfBirth } as Client)
      .subscribe(client => {
        this.getclients();
      });
  }

  delete(client: Client): void {
    this.clientService.deleteclient(client.id).subscribe(client => {
      this.getclients();
    });
  }

}
