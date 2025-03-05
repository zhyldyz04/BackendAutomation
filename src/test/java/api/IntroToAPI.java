package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class IntroToAPI {
    public static void main(String[] args) {

        String baseUri = "https://backend.cashwise.us";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3NDI5NTQ3MzcsImlhdCI6MTc0MDM2MjczNywidXNlcm5hbWUiOiJqaWthNDFAZ21haWwuY29tIn0.D5-2kEI9q08LfIfCNFvhG_2xZfBo2rAqJsYBgVZ-oOefMgZSQMtxYn1Wm6h4uW33xxhyzH18GEH8T6wMLcR7NQ";

//        RestAssured.given().auth().oauth2(token).baseUri(baseUri).
//                when().get("/api/myaccount/tags/all").then().statusCode(200);
//
//        RestAssured.given().auth().oauth2(token).baseUri(baseUri).
//                queryParam("isArchived", false).queryParam("page", 1).
//                queryParam("size", 2).
//                when().
//                get("/api/myaccount/sellers").then().statusCode(200);



        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 2);

        Response response = RestAssured.given().auth().oauth2(token).
                params(params).get(baseUri + "/api/myaccount/sellers");
        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());

      String firstSellerID = response.jsonPath().getString("responses.seller_id[0]");
        System.out.println(firstSellerID);

        Response response1 = RestAssured.given().auth().oauth2(token).
                get(baseUri + "/api/myaccount/sellers/" + firstSellerID);
        System.out.println(response1.getStatusCode());
        System.out.println(response1.prettyPrint());
        String actualSellerID = response1.jsonPath().get("seller_id").toString();
        Assert.assertEquals(firstSellerID, actualSellerID);




    }
}
