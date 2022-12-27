package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestUtils {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
        response = given()
                .when()
                .get("")
                .then().statusCode(200); //method type of this is validatable response
    }

    @Test
    public void verifyUserCreatedSuccessfully() {

        UserPojo userPojo = new UserPojo();

        userPojo.setName("Pratik Patel");
        userPojo.setEmail("pratik.patel" + getRandomValue() + "@email.com");
        userPojo.setGender("Male");
        userPojo.setStatus("active");

        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer eff37b7175d064e16f1d221454ff6e18a74df24bbbd254d766ed3435f0ec8651")
                .when()
                .body(userPojo)
                .post();
        response.prettyPrint();
        response.then().log().all().statusCode(201);

    }

    @Test
    public void verifyUserUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();

        userPojo.setName("Pratik Patel");
        userPojo.setEmail("pratik1.patel" + getRandomValue() + "@email.com");
        userPojo.setGender("Male");
        userPojo.setStatus("active");

        Response response = given()

                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer eff37b7175d064e16f1d221454ff6e18a74df24bbbd254d766ed3435f0ec8651")
                .body(userPojo)
                .pathParam("id", 11711) //passing parameter to .get()
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();


    }

    @Test
    public void VerifyUserDeleteSuccessfully() {

        UserPojo userPojo = new UserPojo();
        Response response = given()

                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer eff37b7175d064e16f1d221454ff6e18a74df24bbbd254d766ed3435f0ec8651")
                .body(userPojo)
                .pathParam("id", 11711) //passing parameter to .get()
                .when()
                .delete("/{id}");
        response.then().statusCode(204);
        response.prettyPrint();

    }
}