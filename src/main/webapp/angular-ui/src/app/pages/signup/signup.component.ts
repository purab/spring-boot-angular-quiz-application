import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user.model';
import { UserService } from 'src/app/service/user.service';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user:User = new User();

  constructor(
    private userService:UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  formSubmit(){
    console.log(this.user);
    this.userService.addUser(this.user)
    .subscribe(data => console.log(data), error => console.log(error));
    this.user = new User();
    //this.router.navigate(['/users']);    
  }

}
