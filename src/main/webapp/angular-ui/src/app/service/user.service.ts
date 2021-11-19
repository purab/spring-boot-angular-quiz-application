import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user.model';
import baseUrl from './helper';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http:HttpClient,
    private router: Router
  ) { }
  
  public addUser(user:any) {
    return this.http.post<User>(`${baseUrl}/user/`, user);
  }

  public generateToekn(loginData:any) {
    return this.http.post(`${baseUrl}/generate-token`, loginData);
  }

  public loginUser(token:any) {
    localStorage.setItem('userToken',token);
    return true;
  }

  public getToken() {
    let token=localStorage.getItem('userToken');
    return token;
  }

  public getCurrentUser(){
    return this.http.get(`${baseUrl}/current-user`);
  }

  //user is logged in or not
  public isLoggedIn(){
    let token=localStorage.getItem('userToken');
    if(token==undefined || token==''||token==null) {
      return false
    } else {
      return true;
    }    
  }

  public logout() {
    localStorage.removeItem('userToken');
    localStorage.removeItem('user');
    this.router.navigate(['/login']);
    return true;
  }

  //set user details
  public setUser(user:any) {
    localStorage.setItem('user',JSON.stringify(user));
  }

  public getUser() {
    let usrStr = localStorage.getItem('user');
    if(usrStr!=null) {
      return JSON.parse(usrStr);
    } else {
      this.logout();
      return null;
    }
  }

  public getUserRole(){
    let user=this.getUser();
    if(user!=null) {
      return user.authorities[0].authority;
    } else {
      return false;
    }    
  }

}
