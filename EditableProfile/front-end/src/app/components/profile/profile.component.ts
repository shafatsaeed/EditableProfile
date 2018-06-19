import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {AccountService} from "../../services/account.service";
import {User} from "../../model/model.user";
import {Router} from "@angular/router";
import {Http, Response} from "@angular/http";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProfileComponent implements OnInit {
  currentUser: User;
  genderList:[{id: string, name: string}];
  maritalList:[{id: string, name: string}];
  religionList:[{id: string, name: string}];
  ethnicityList:[{id: string, name: string}];
  figureList:[{id: string, name: string}];
  constructor(public authService: AuthService, public router: Router,public http: Http,public accountService: AccountService) {	
	this.getChoiceList();
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));	
	this.genderList =  JSON.parse(localStorage.getItem('genderList'));
	this.maritalList =  JSON.parse(localStorage.getItem('maritalList'));
	this.religionList =  JSON.parse(localStorage.getItem('religionList'));
	this.ethnicityList =  JSON.parse(localStorage.getItem('ethnicityList'));
	this.figureList = JSON.parse(localStorage.getItem('figureList'));
  }

  ngOnInit() {		
  }

// login out from the app
  logOut() {
    this.authService.logOut()
      .subscribe(
        data => {
          this.router.navigate(['/login']);
        },
        error => {

        });
  }
  update() {
    this.accountService.updateAccount(this.currentUser).subscribe(data => {
        this.router.navigate(['/login']);
      }, err => {
        console.log(err);
        this.errorMessage = "username already exist";
      }
    )
  }
  getChoiceList(){
	  this.accountService.getChoice()     
	  .subscribe(
        data => {
          //this.router.navigate(['/login']);
        },
        error => {

        });
  }
}
