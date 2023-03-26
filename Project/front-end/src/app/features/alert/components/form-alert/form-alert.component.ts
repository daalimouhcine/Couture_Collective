import { Component,OnInit } from '@angular/core';
import { Alert } from '../../interfaces/alert';

@Component({
  selector: 'app-form-alert',
  templateUrl: './form-alert.component.html',
  styleUrls: ['./form-alert.component.scss']
})
export class FormAlertComponent implements OnInit {

  message: string = ""
  alertStyle: Alert = new Alert();
  
  constructor() {  }

  ngOnInit(): void {
  }


  makeAlertStyle(color: string, message: string): void {
    this.message = message;
    this.alertStyle.svgstyle = `flex-shrink-0 w-5 h-5 text-${color}-700`
    this.alertStyle.message = `ml-3 text-sm font-medium text-${color}-700`
    this.alertStyle.button = `ml-auto -mx-1.5 -my-1.5 bg-${color}-100 text-${color}-500 rounded-lg focus:ring-2 focus:ring-${color}-400 p-1.5 hover:bg-${color}-200 inline-flex h-8 w-8 dark:bg-${color}-200`
    this.alertStyle.bg = `w-64 text-xs flex p-4 mb-4 bg-${color}-100 rounded-lg`
    setTimeout(() => {this.message = ""},4000)  
  }

}
