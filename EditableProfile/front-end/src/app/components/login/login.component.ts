import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {User} from "../../model/model.user";
import {AuthService} from "../../services/auth.service";
import {AccountService} from "../../services/account.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {
  user: User=new User();
  errorMessage:string;
  constructor(public authService :AuthService, public router: Router, public accountService: AccountService) { }



  ngOnInit() {
  }

  login(){
    this.authService.logIn(this.user)
      .subscribe(data=>{
        this.router.navigate(['/profile']);
        },err=>{
        this.errorMessage="error :  Username or password is incorrect";
        }
      )
  }
  userlogin(){
    this.authService.logIn(this.user).subscribe(data=>{		
        this.router.navigate(['/profile']);
        },err=>{
			console.log(err);
			this.errorMessage="error :  Username or password is incorrect";
        }
      )
  }
}
