import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { BookService } from '../services/book.service';
import { Book } from '../models/book';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit{
  
  search='';
  books!: Book[];
  
  constructor (private bookService: BookService, private router: Router) {
   
  }
  ngOnInit(): void {

    this.bookService.getListaAllBooks().subscribe(response => {
      this.books = response;
    })
  }

  showDetail(id: any){
      this.router.navigate(['/book-detail', id]);
  }

  showDetailReturn(id: any){
    this.router.navigate(['/book-detail-return', id]);
}

}
