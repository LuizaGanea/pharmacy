import { Medicine } from "./medicine";

export interface Sale {
    id: number;
    date_sale: Date;
    quantity: number;
    id_client: number;
    id_medicament: number;
    medicament?: Medicine
  }
  
  
  /*
  Copyright Google LLC. All Rights Reserved.
  Use of this source code is governed by an MIT-style license that
  can be found in the LICENSE file at https://angular.io/license
  */