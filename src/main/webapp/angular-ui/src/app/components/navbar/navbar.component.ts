import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn=false;
  user:any;

  constructor(public userService:UserService,private router:Router) { }

  ngOnInit(): void {
    this.isLoggedIn =this.userService.isLoggedIn();
    this.user=this.userService.getUser();
    console.log(this.user);
    this.userService.loginStatusSubject.asObservable().subscribe(data=>{
      this.isLoggedIn =this.userService.isLoggedIn();
      this.user=this.userService.getUser();
    })
  }

  public navLogout() {
    this.isLoggedIn=false;
    this.user=null;
    this.userService.logout();
    this.router.navigate(['/login']);
  }

}
