import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
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
    private router: Router,
    private snack:MatSnackBar
  ) { }

  ngOnInit(): void {
  }

  formSubmit(){

    if(this.user.username==""||this.user.username==null) {
      this.snack.open("username is required!","ok",{
        duration:3000,
        verticalPosition:'top',
        horizontalPosition:'right'

      });
      return;
    }

    console.log(this.user);
    this.userService.addUser(this.user)
    .subscribe(
      (data) => {
        console.log(data);
        this.user = new User();
        this.router.navigate(['/login']);            
        this.snack.open("user registerd successfuly","ok",{duration:3000,verticalPosition:'top',horizontalPosition:'right'});
         
      }, 
      error => console.log(error)
      );
    
  }

}
