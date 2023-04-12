import { Component, Input, EventEmitter, Output, OnInit } from '@angular/core';
import { Work } from '../../interfaces/work';
import { FeaturesService } from 'src/app/core/services/features.service';
import Swal from 'sweetalert2';
import { AuthService } from 'src/app/core/services/auth.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-work-card',
  templateUrl: './work-card.component.html',
  styleUrls: ['./work-card.component.scss']
})
export class WorkCardComponent implements OnInit {
  @Input() work: Work = new Work();
  @Output() workDeleted = new EventEmitter();

  showOptions = false;

  constructor(private featuresService: FeaturesService, private authService: AuthService, private _http: HttpClient) {}

  ngOnInit(): void {}

  deleteWork(id: number | undefined) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You will not be able to recover this imaginary file!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
    }).then((result) => {
      if (result.isConfirmed) {
        this.featuresService.delete(id, 'project/delete/').subscribe((response) => {
          console.log(response);
          if (response) {
            Swal.fire('Deleted!', 'Your imaginary file has been deleted.', 'success');
            this.workDeleted.emit();
          } else {
            Swal.fire('Error!', 'Something went wrong.', 'error');
          }
        });
      }
    });
  }

  switchCompleted(id: number | undefined) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You will update the status for this project!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
    }).then((result) => {
      if (result.isConfirmed) {
        this._http.get('http://localhost:8080/api/project/switchDone/' + id).subscribe((response) => {
          console.log(response);
          if (response) {
            Swal.fire('Updated!', 'The status for completion of this project has updated.', 'success');
            this.workDeleted.emit();
          } else {
            Swal.fire('Error!', 'Something went wrong.', 'error');
          }
        });
      }
    });
  }

  switchPaid(id: number | undefined) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You will update the payment status for this project!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
    }).then((result) => {
      if (result.isConfirmed) {
        this._http.get('http://localhost:8080/api/project/switchPaid/' + id).subscribe((response) => {
          console.log(response);
          if (response) {
            Swal.fire('Updated!', 'The status for payment of this project has updated.', 'success');
            this.workDeleted.emit();
          } else {
            Swal.fire('Error!', 'Something went wrong.', 'error');
          }
        });
      }
    });
  }

}
