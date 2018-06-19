package com.social.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.social.beans.City;
import com.social.beans.Ethnicity;
import com.social.beans.Figure;
import com.social.beans.Gender;
import com.social.beans.MaritalStatus;
import com.social.beans.Religion;
import com.social.beans.SingleChoiceAttributes;
import com.social.entities.User;
import com.social.services.UserService;
import com.social.util.CustomErrorType;
import java.io.FileReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shafat S
 *
 */
@RestController
@RequestMapping("account")
public class AccountController {

    public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private UserService userService;

    // request method to create a new account by a guest
    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User newUser) throws Exception {
        if (userService.find(newUser.getUsername()) != null) {
            logger.error("username Already exist " + newUser.getUsername());
            return new ResponseEntity(
                    new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
                    HttpStatus.CONFLICT);
        }
        newUser.setRole("USER");
        //newUser.setProfilePic(file.getBytes());
        return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
    }

    // request method to create a new account by a guest
    @CrossOrigin
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody User newUser) {
        if (userService.find(newUser.getUsername()) == null) {
            logger.error("Incorrect data for username  " + newUser.getUsername());
            return new ResponseEntity(
                    new CustomErrorType("user with username " + newUser.getUsername() + "does not exist for updation "),
                    HttpStatus.CONFLICT);
        }
        newUser.setRole("USER");

        return new ResponseEntity<User>(userService.update(newUser), HttpStatus.CREATED);
    }

    // this is the login api/service
    @CrossOrigin
    @RequestMapping("/login")
    public ResponseEntity<?> user(Principal principal) {
        logger.info("user logged " + principal);
        return new ResponseEntity<User>(userService.find(principal.getName()), HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping(value = "/choiceList", method = RequestMethod.GET)
    public ResponseEntity<?> getSingleChoiceAttributes() {
        Resource res = resourceLoader.getResource("classpath:/json/single_choice_attributes.json");
        Resource res2 = resourceLoader.getResource("classpath:/json/cities.json");
        SingleChoiceAttributes sca = new SingleChoiceAttributes();
        List<Gender> genderList = new ArrayList<Gender>();
        List<MaritalStatus> maritalStatusList = new ArrayList<MaritalStatus>();
        List<Religion> religionList = new ArrayList<Religion>();
        List<Ethnicity> ethnicityList = new ArrayList<Ethnicity>();
        List<Figure> figureList = new ArrayList<Figure>();
        List<City> cityList = new ArrayList<City>();
        Gson gson = new Gson();
        try {
            JsonObject jsonObject = gson.fromJson(new FileReader(res.getFile()), JsonObject.class);
            JsonArray genderArray = jsonObject.getAsJsonArray("gender");
            JsonArray maritalArray = jsonObject.getAsJsonArray("marital_status");
            JsonArray religionArray = jsonObject.getAsJsonArray("religion");
            JsonArray ethnicityArray = jsonObject.getAsJsonArray("ethnicity");
            JsonArray figureArray = jsonObject.getAsJsonArray("figure");
            //gender
            for (JsonElement genderElement : genderArray) {
                JsonObject jo = genderElement.getAsJsonObject();
                Gender gender = new Gender();
                gender.setId(jo.get("id").getAsString());
                gender.setName(jo.get("name").getAsString());
                genderList.add(gender);
            }
            sca.setGenderList(genderList);

            //Marital
            for (JsonElement maritalElement : maritalArray) {
                JsonObject jo = maritalElement.getAsJsonObject();
                MaritalStatus mStatus = new MaritalStatus();
                mStatus.setId(jo.get("id").getAsString());
                mStatus.setName(jo.get("name").getAsString());
                maritalStatusList.add(mStatus);
            }
            sca.setMaritalStatusList(maritalStatusList);

            //Religion
            for (JsonElement religionElement : religionArray) {
                JsonObject jo = religionElement.getAsJsonObject();
                Religion religion = new Religion();
                religion.setId(jo.get("id").getAsString());
                religion.setName(jo.get("name").getAsString());
                religionList.add(religion);
            }
            sca.setReligionList(religionList);

            //Ethnicity
            for (JsonElement ethnicityElement : ethnicityArray) {
                JsonObject jo = ethnicityElement.getAsJsonObject();
                Ethnicity ethnicity = new Ethnicity();
                ethnicity.setId(jo.get("id").getAsString());
                ethnicity.setName(jo.get("name").getAsString());
                ethnicityList.add(ethnicity);
            }
            sca.setEthnicityList(ethnicityList);

            //Figure
            for (JsonElement figureElement : figureArray) {
                JsonObject jo = figureElement.getAsJsonObject();
                Figure figure = new Figure();
                figure.setId(jo.get("id").getAsString());
                figure.setName(jo.get("name").getAsString());
                figureList.add(figure);
            }
            sca.setFigureList(figureList);

            //Cities
            JsonObject jsonObject2 = gson.fromJson(new FileReader(res2.getFile()), JsonObject.class);
            JsonArray citiesArray = jsonObject2.getAsJsonArray("cities");
            for (JsonElement citiesElement : citiesArray) {
                JsonObject jo = citiesElement.getAsJsonObject();
                City city = new City();
                city.setLat(jo.get("lat").getAsString());
                city.setLon(jo.get("lon").getAsString());
                city.setCity(jo.get("city").getAsString());
                cityList.add(city);
            }
            sca.setCityList(cityList);

        } catch (Exception ee) {
            System.out.println(ee);
        }

        return new ResponseEntity<SingleChoiceAttributes>(sca, HttpStatus.ACCEPTED);

    }

}
