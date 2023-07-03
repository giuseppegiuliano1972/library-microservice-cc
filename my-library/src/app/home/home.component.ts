import { Component } from '@angular/core';

import { BookService } from '../services/book.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor (private bookService: BookService){}

  getgreet(){
    this.bookService.getGreet().subscribe(
      data => {
        console.log(data);
      },
      err => {
        console.log(err.error);
      }
    );
  }

}
