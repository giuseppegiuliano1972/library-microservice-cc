import { Component, OnInit } from '@angular/core';

import { LendingService } from '../services/lending.service';
import { Lending } from '../models/lending';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-lending',
  templateUrl: './lending.component.html',
  styleUrls: ['./lending.component.css']
})
export class LendingComponent implements OnInit {
  
  lending!: Lending[];
  form!: FormGroup;

  constructor (private lendingService: LendingService, private fb: FormBuilder) {
   
  }
  ngOnInit(): void {
    this.form = this.fb.group({
      name: this.fb.array([])
    })
    this.lendingService.getListaPrenotabili().subscribe(response => {
        this.lending = response;
    })
  }

  saveBook(){
    console.log(this.lending);
  }

  
}
