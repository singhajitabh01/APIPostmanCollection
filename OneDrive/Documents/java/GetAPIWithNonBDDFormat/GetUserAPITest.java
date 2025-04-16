package GetAPIWithNonBDDFormat;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.annotations.Test;

public class GetUserAPITest {
	
	@Test
	public void geContactsTest() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com"; 
		
		RequestSpecification request = RestAssured.given();//return type is RequestSpecification
		
		request.header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2N2IxYTExODk5MzMwMTAwMTM2ZDVlN2EiLCJpYXQiOjE3NDQ3MTAzNjZ9.Nr9pEUA-hQlkysflqWc0-rCALYfGxSsseCFIOiXqwhE");
		
		Response response = request.get("/contacts"); //return type is Response
		
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
		
		response.prettyPrint();
		//if you only want a single header value 
		String contentType = response.header("content-type");
		System.out.println(contentType);
		
		//for multiple Header value 
		Headers headers = response.headers();
		//Total size of header
		List<Header>HeadersList = headers.asList();
		System.out.println(HeadersList.size());
		
		//print all name and value of Headers 
		
		for(Header h:HeadersList) {
			String name = h.getName();
			String value = h.getValue();
			System.out.println(name + "===" + value);
		}
		

	}
}
