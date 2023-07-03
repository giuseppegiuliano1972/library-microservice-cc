import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Book } from '../models/book';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

//const URL_BIBLIO = 'http://localhost:8081/libri/';
const URL_BIBLIO = environment.bookApi;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient ) { }

  getListaAllBooks(): Observable<Book[]> {

    return this.http.get<Book[]>(URL_BIBLIO + 'lista/libri');
  }

  getBookById(id: any): Observable<Book> {

    return this.http.get<Book>(URL_BIBLIO + id);
  }

//test
getGreet(){
 
  return this.http.get<any>('http://localhost:8082/member/getgreet');
}

}
