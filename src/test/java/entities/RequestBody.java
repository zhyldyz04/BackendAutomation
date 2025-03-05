package entities;

import lombok.Data;

@Data
public class RequestBody {

    private String email;
    private String password;
    private String name_tag;
    private  String description;

    //requestBody for creating product
    private String product_title;
    private int product_price;
    private int service_type_id;
    private int category_id;
    private String product_description;
    private String date_of_payment;
    private int remind_before_day;





}
