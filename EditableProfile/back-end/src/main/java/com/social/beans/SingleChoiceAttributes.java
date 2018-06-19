package com.social.beans;

import java.util.List;

/**
 *
 * @author ssaeed
 */
public class SingleChoiceAttributes {

    private List<Gender> genderList;
    private List<Ethnicity> ethnicityList;
    private List<Figure> figureList;
    private List<MaritalStatus> maritalStatusList;
    private List<Religion> religionList;
    private List<City> cityList;

    public List<Gender> getGenderList() {
        return genderList;
    }

    public void setGenderList(List<Gender> genderList) {
        this.genderList = genderList;
    }

    public List<Ethnicity> getEthnicityList() {
        return ethnicityList;
    }

    public void setEthnicityList(List<Ethnicity> ethnicityList) {
        this.ethnicityList = ethnicityList;
    }

    public List<Figure> getFigureList() {
        return figureList;
    }

    public void setFigureList(List<Figure> figureList) {
        this.figureList = figureList;
    }

    public List<MaritalStatus> getMaritalStatusList() {
        return maritalStatusList;
    }

    public void setMaritalStatusList(List<MaritalStatus> maritalStatusList) {
        this.maritalStatusList = maritalStatusList;
    }

    public List<Religion> getReligionList() {
        return religionList;
    }

    public void setReligionList(List<Religion> religionList) {
        this.religionList = religionList;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

}
