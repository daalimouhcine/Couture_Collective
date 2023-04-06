import { Component, OnInit } from '@angular/core';
import { Client } from '../../interfaces/client';
import { FeaturesService } from 'src/app/core/services/features.service';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-all-clients',
  templateUrl: './all-clients.component.html',
  styleUrls: ['./all-clients.component.scss']
})
export class AllClientsComponent implements OnInit{

  clients: Client[] = [];

  constructor(private featuresService: FeaturesService, private authService: AuthService) { }

  ngOnInit(): void {
    this.featuresService.getAll(`client/byTailor/${this.authService.getUserFromLocalStorage().id}`).subscribe((response) => {
      this.clients = response;
    });

  }


}
