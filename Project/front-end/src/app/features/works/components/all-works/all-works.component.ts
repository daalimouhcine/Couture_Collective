import { Component, OnInit } from '@angular/core';
import { Work } from '../../interfaces/work';
import { FeaturesService } from 'src/app/core/services/features.service';
import { AuthService } from 'src/app/core/services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-all-works',
  templateUrl: './all-works.component.html',
  styleUrls: ['./all-works.component.scss'],
})
export class AllWorksComponent implements OnInit {

  works: Work[] = [];
  constructor(private featuresService: FeaturesService, private authService: AuthService) {}

  ngOnInit(): void {
    this.getWorks();
  }

  getWorks() {
    this.featuresService.getAll(`project/byTailor/${this.authService.getUserFromLocalStorage().id}`).subscribe((response) => {
      this.works = response;
      console.log(response);
    });
  }


}
