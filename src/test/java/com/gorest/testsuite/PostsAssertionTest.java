package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        //   RestAssured.port = 2
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200); //method type of this is validatable response
    }

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("total.size().toString()", equalTo("25"));
    }


    //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum. ”
    @Test
    public void test002() {
        response.body("title[2]", equalTo("Ad ipsa coruscus ipsam eos demitto centum."));
        response.body("id[2]", equalTo(2730));
    }

    //3. Check the single user_id in the Array list (5522)
    @Test
    public void test003() {
        response.body("user_id[4].toString()", equalTo("5522"));
    }

    //4. Check the multiple ids in the ArrayList (2612, 2636,2662)
    @Test
    public void test004() {
        response.body("id.sort()", hasItems(2612, 2636, 2662));
    }

    //    5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
    //    spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //    Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //    Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et//    antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //    cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //    adflicto. Assentator umquam pel."”
    @Test
    public void test005() {
        response.body("body[0]", equalTo("Demitto tres sit. Catena ver triginta. Super viscus sponte. Cernuus sed tabernus. Iste calcar tumultus. Turpis vinco administratio. Depereo ad vivo. Tendo conitor canonicus. Verus anser cras. Claro deporto succurro. Nihil suspendo volva. Clamo consequatur ut. Taceo degusto hic. Usque et canis. Vinum copiose sustineo. Uterque toties cunae."));
        response.body("user_id[0]", equalTo(5585));
    }

}


