import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse} from '@angular/common/http';

import { Lending } from '../models/lending';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';


//const URL_API = 'http://localhost:8083/prestito/';
const URL_API = environment.lendingApi;


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class LendingService {

  lending!: Lending[];

  constructor(private http: HttpClient) { }

  getListaPrenotabili(): Observable<Lending[]> {

    return this.http.get<Lending[]>(URL_API + 'lista/libri/disponibili');
  }

  getListaPrestati(): Observable<Lending[]> {

      return this.http.get<Lending[]>(URL_API + 'lista/libri/borrowed');
  }

  gePrestitoByIdBook(idBook: any): Observable<Lending> {

    return this.http.get<Lending>(URL_API + 'info/' + idBook);
  }

  postLibro(dataLend: Lending): Observable<Lending> {
    console.log("LENDING:", dataLend);
    const jsonObject: Lending = {
      "BOOK_ID": dataLend.BOOK_ID,
      "LEND_ID": dataLend.LEND_ID,
      "ID_MEMBER": 0,
      "USERID": '',
      "TITOLO": '',
      "COD_FISCALE": dataLend.COD_FISCALE,
      "STATUS": '',
      "COGNOME_MEMBER": '',
      "NOME_MEMBER": '',
      "ID_CARD": 0,
      "DATE_LENDING": dataLend.DATE_LENDING,
       "DUE_RETURN_DATE": dataLend.DUE_RETURN_DATE,
       RETURN_DATE: dataLend.DATE_LENDING
    }; 
      
    let json = JSON.stringify(jsonObject); 
    console.log("LENDING2:", json);
    return this.http.post<Lending>(URL_API + 'libro/esegui', json, httpOptions) ;

  
  }
      
  putReturnBook(lending: Lending): Observable<Lending> {
    console.log("LENDING:", lending);
    const jsonObject: Lending = {
      "BOOK_ID": lending.BOOK_ID,
      "LEND_ID": lending.LEND_ID,
      "ID_MEMBER": 0,
      "USERID": '',
      "TITOLO": '',
      "COD_FISCALE": '',
      "STATUS": '',
      "COGNOME_MEMBER": '',
      "NOME_MEMBER": '',
      "ID_CARD": 0,
      "DATE_LENDING": lending.DATE_LENDING,
      "RETURN_DATE": lending.RETURN_DATE
      //"DUE_RETURN_DATE": lending.DUE_RETURN_DATE
    }; 
      
    let json = JSON.stringify(jsonObject); 
    console.log("LENDING2:", json);
    console.log("URL:", URL_API + 'libro/return');
    return this.http.put<Lending>(URL_API + 'libro/return', json, httpOptions);
    
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }

}
