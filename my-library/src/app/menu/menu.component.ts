import { Component, OnInit } from '@angular/core';
import { StorageService } from '../services/storage.service';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLoggedIn = false;

  constructor(private storageService: StorageService, private authService: AuthService, private router: Router ){
    this.isLoggedIn = this.storageService.isLoggedIn();
    console.log("LOGGED: ", this.isLoggedIn);
  }

  ngOnInit(): void {
    
    this.isLoggedIn = this.storageService.isLoggedIn();
    console.log("LOGGED: ", this.isLoggedIn);
  }
  
  logout(): void {
      this.storageService.clean();
      this.router.navigate(['/login']);
      window.location.reload();
  }
  

}
