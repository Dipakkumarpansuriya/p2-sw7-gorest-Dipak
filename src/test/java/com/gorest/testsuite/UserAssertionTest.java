package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        //   RestAssured.port = 2
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200); //method type of this is validatable response
    }

    //1. Verify the if the total record is 20
    @Test
    public void test1() {
        response.body("total.size().toString()", equalTo("20"));
    }

    //2. Verify the if the name of id = 5487 is equal to ”Trilochan Gupta”
    @Test
    public void test2() {
        response.body("[5].name", equalTo("Trilochan Gupta"));
    }

    //3. Check the single ‘Name’ in the Array list (Raj Patil)
    @Test
    public void test3() {
        response.body("name", hasItem("Raj Patil"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Dhana Kaul,Udit Menon, Kashyap Prajapat)
    @Test
    public void test4() {
        response.body("name", hasItems("Rameshwar Varman","Raj Patil","Aashritha Bhattathiri"));
}
    //5. Verify the emai of userid = 5306 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test5() {
        response.body("email", hasItem("nagabhushanam_johar@abshire-tromp.net"));
    }
    //6. Verify the status is “Active” of user name is “Subodh Menon”
    @Test
    public void test6() {
        response.body("status[5]", equalTo("active"));
        response.body("name[5]", equalTo("Subodh Menon"));
    }

    //7. Verify the Gender = male of user name is "Trilochan Gupta"
    @Test
    public void test7() {
        response.body("gender[3]", equalTo("male"));

    }
}


