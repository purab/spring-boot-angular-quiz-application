import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { UserService } from "./user.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor
{

    constructor(private userService:UserService){}


    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        //add jwt token
        let authReq=req;
        const token = this.userService.getToken();
        if(token!=null) {
            authReq=authReq.clone({setHeaders:{Authorization:`Bearer ${token}`}});
            console.log('coming here in Auth Interceptor.......')
        }
        return next.handle(authReq);
    }
    
}

export const authInterceptorProviders=[
    {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true
    }
]