import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {User} from "../../model/model.user";
import {AccountService} from "../../services/account.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class RegisterComponent implements OnInit {
  user: User = new User();
  errorMessage: string;
  genderList:[{id: string, name: string}];
  maritalList:[{id: string, name: string}];
  religionList:[{id: string, name: string}];
  ethnicityList:[{id: string, name: string}];
  figureList:[{id: string, name: string}];
  cityList:[{lat: string, lon: string, city: string}];
  

  constructor(public accountService: AccountService, public router: Router) {
	  this.getChoiceList();
	  this.genderList =  JSON.parse(localStorage.getItem('genderList'));
	  this.maritalList =  JSON.parse(localStorage.getItem('maritalList'));
	  this.religionList =  JSON.parse(localStorage.getItem('religionList'));
	  this.ethnicityList =  JSON.parse(localStorage.getItem('ethnicityList'));
	  this.figureList = JSON.parse(localStorage.getItem('figureList'));
	  this.cityList = JSON.parse(localStorage.getItem('cityList'));
  }

  ngOnInit() {
  }

  register() {
    this.accountService.createAccount(this.user).subscribe(data => {
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
  onFileChange(event){
    this.file = event.target.files;
	this.user.file = this.file;
    console.log(event);
  }
}
