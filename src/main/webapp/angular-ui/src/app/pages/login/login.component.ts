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
                this.userService.loginStatusSubject.next(true);
                //redirect user - admin dashboard
                //redirect user - normal user dashboard 
                if(this.userService.getUserRole()=='ADMIN') {
                  this.router.navigate(['/admin']);
                } else if(this.userService.getUserRole()=='USER'){
                  this.router.navigate(['/user']);
                } else{
                  this.userService.logout();
                  //location.reload();
                }
            },
            (error) => {
              console.log(error)
              this.snack.open("Invalid Details!","ok",{
                duration:3000,
                verticalPosition:'top',
                horizontalPosition:'right'
              });
            }
          );
      }, 
      (error) => {
        console.log(error);
        this.snack.open("Invalid Details!","ok",{
          duration:3000,
          verticalPosition:'top',
          horizontalPosition:'right'
        });
      }
      );
    //this.user = new User();
    //this.router.navigate(['/users']);
  }

}
