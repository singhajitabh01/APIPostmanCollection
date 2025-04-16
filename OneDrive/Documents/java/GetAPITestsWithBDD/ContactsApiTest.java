package GetAPITestsWithBDD;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class ContactsApiTest {
	
	@BeforeMethod	
	public void setUp() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
	}
	@Test(priority = 1)
	public void getContactsAPITest() {
				
		given().log().all()
		.header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2N2IxYTExODk5MzMwMTAwMTM2ZDVlN2EiLCJpYXQiOjE3NDQzNTYxODd9.uAG9wO5snVPuMnGBCUFFAdmZzD2E2vIS20ZVx5zVfkA")
		.when()
			.get("/contacts")
			.then().log().all()
				.assertThat().statusCode(200)
				.and()
				.contentType(ContentType.JSON);
	}
	
	@Test(priority = 2)	//Here we check negative test case
	public void getContactsAPIAuthError() {
		given().log().all()
		.header("Authorization","Bearer Ajitabh")
		.when().log().all()
		.get("/contacts")
		.then().log().all()
		.assertThat()
		.statusCode(401).and().contentType(ContentType.JSON);		
	}
	
	@Test(priority = 3)	//this function is used to verify authentication with body
	public void getContactAPIInvalidTokenTest() {
		String errorMsg = given().log().all()
					.header("Authorization","Bearer Aman")
							.when().log().all()
								.get("/contacts")
									.then()
										.extract()
												.path("error");
											System.out.println(errorMsg);
												Assert.assertEquals(errorMsg, "Please authenticate.");
	}
}
