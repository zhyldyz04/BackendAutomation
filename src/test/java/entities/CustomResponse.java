package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponse {

    private String id;
    private String name_tag;
    private String creation_date;


    //response for creating product
    private String product_title;
    private int product_price;
    private int service_type_id;
    private int category_id;
    private String product_description;
    private String date_of_payment;
    private int remind_before_day;


}
