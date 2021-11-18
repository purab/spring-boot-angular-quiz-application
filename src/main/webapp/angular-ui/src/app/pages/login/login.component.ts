import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData={
    username:'',
    password:''
  }

  constructor(
    private userService:UserService,
    private router: Router,
    private snack:MatSnackBar
  ) { }

  ngOnInit(): void {
  }

  formSubmit() {
    if(this.loginData.username==""||this.loginData.username==null) {
      this.snack.open("username is required!","ok",{
        duration:3000,
        verticalPosition:'top',
        horizontalPosition:'right'
      });
      return;
    }

    console.log(this.loginData);
    this.userService.generateToekn(this.loginData)
    .subscribe(
      (data:any) => {
        console.log(data)
        this.userService.loginUser(data.token);
        this.userService.getCurrentUser()
          .subscribe(
            (user:any) => {
                this.userService.setUser(user);
                console.log(user);
                //redirect user - admin dashboard 
                
                //redirect user - normal user dashboard 
                this.router.navigate(['/']);
            },
            (error) => console.log(error)
          );
      }, 
      (error) => console.log(error)
      );
    //this.user = new User();
    //this.router.navigate(['/users']);
  }

}
