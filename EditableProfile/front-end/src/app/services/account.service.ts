import { Injectable } from '@angular/core';
import {User} from "../model/model.user";
import {Http, Headers, RequestOptions, Response} from "@angular/http";
import {AppComponent} from "../app.component";

@Injectable()
export class AccountService {
  constructor(public http: Http) { }

  createAccount(user:User){
    return this.http.post(AppComponent.API_URL+'/account/register',user)
      .map(resp=>resp.json());
  }
  updateAccount(user:User){
    return this.http.post(AppComponent.API_URL+'/account/update',user)
      .map(resp=>resp.json());
  }
  loginAccount(user:User){
    return this.http.post(AppComponent.API_URL+'/account/userlogin',user)
      .map(resp=>resp.json());
  }
  getChoice(){
	return this.http.get(AppComponent.API_URL+"/account/choiceList")
      .map((response: Response) => {      
      let genderList = response.json().genderList;
	  let maritalList = response.json().maritalStatusList;
	  let religionList = response.json().religionList;
	  let ethnicityList = response.json().ethnicityList;
	  let figureList = response.json().figureList;
      if (genderList) {        
        localStorage.setItem('genderList', JSON.stringify(genderList));
		localStorage.setItem('maritalList', JSON.stringify(maritalList));
		localStorage.setItem('religionList', JSON.stringify(religionList));
		localStorage.setItem('ethnicityList', JSON.stringify(ethnicityList));
		localStorage.setItem('figureList', JSON.stringify(figureList));
      }
    }
  } 
}
