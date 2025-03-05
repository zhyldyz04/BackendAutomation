package step_definitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponse;
import entities.RequestBody;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utilities.CashWiseToken;

public class CreateProductSteps {

    RequestSpecification request;
    RequestBody body = new RequestBody();
    String token = CashWiseToken.GetToken();
    Response response;
    CustomResponse responseBody = new CustomResponse();
    ObjectMapper mapper = new ObjectMapper();

    @Given("base cashwise url {string} with valid token")
    public void base_cashwise_url_with_valid_token(String url) {
       request = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).baseUri(url);

    }

    @Then("I provide product_title with {string}")
    public void i_provide_product_title_with(String productTitle) {
        body.setProduct_title(productTitle);
        System.out.println(body.getProduct_title());

    }
    @Then("product price {int}")
    public void product_price(Integer price) {
        body.setProduct_price(price);


    }
    @Then("provide service_type_id with {int}")
    public void provide_service_type_id_with(Integer serviceType) {
        body.setService_type_id(serviceType);

    }
    @Then("provide category_id with {int}")
    public void provide_category_id_with(Integer categoryId) {
        body.setCategory_id(categoryId);
    }
    @Then("provide product_description with {string}")
    public void provide_product_description_with(String productDescription) {
        body.setDescription(productDescription);

    }
    @Then("provide date_of_payment with {string}")
    public void provide_date_of_payment_with(String dateOfPayment) {
       body.setDate_of_payment(dateOfPayment);
    }
    @Then("set remind_before_day to {int}")
    public void set_remind_before_day_to(Integer remindDays) {
        body.setRemind_before_day(remindDays);


    }
    @Then("I hit POST endpoint {string} to create product")
    public void i_hit_post_endpoint_to_create_product(String endpoint) throws JsonProcessingException {
        String requestBody = mapper.writeValueAsString(body);
        request = request.body(requestBody);
        response = request.post(endpoint);


    }
    @Then("verify status code is equal to {int}")
    public void verify_status_code_is_equal_to(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), response.statusCode());

    }
    @Then("verify that product_title equals to {string}")
    public void verify_that_product_title_equals_to(String productTitle) {
        String actualProductTitle = response.jsonPath().getString("product_title");
       Assert.assertEquals(productTitle, actualProductTitle);
    }

}