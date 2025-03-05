package step_definitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.Config;


public class TagSteps {

    RequestSpecification request;
    Response response;
    JSONObject requestBody = new JSONObject();
    String id;

    @Given("base url {string}")
    public void base_url(String url) {
        request = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).baseUri(url);

    }
    @When("I provide valid authorization token")
    public void i_provide_authorization_token() {
        request = request.auth().oauth2(Config.getProperty("cashwiseToken"));

    }
    @When("I provide {string} with {string}")
    public void i_provide_with(String key, String value) {
        requestBody.put(key, value);
        request = request.body(requestBody.toString());


    }
    @When("I hit POST endpoint {string}")
    public void i_hit_post_endpoint(String endpoint) {
       response = request.post(endpoint);
    }
    @Then("verify status code is {int}")
    public void verify_status_code_is(Integer statusCode) {
        System.out.println(response.prettyPrint());
        Assert.assertEquals((int) statusCode, response.getStatusCode());


    }
    @Then("verify response body contains {string} with {string}")
    public void verify_response_body_contains_with(String key, String value) {
        String expectedValue = response.jsonPath().get(key);
        Assert.assertEquals(expectedValue, value);

    }

    @Then("I retrieve id {string}")
    public void i_retrieve_id(String id) {
        this.id = response.jsonPath().getString(id);


    }
    @Then("I hit DELETE endpoint {string}")
    public void i_hit_delete_endpoint(String endpoint) {
        response = request.delete(endpoint + id);
        System.out.println(response.prettyPrint());

    }





}
