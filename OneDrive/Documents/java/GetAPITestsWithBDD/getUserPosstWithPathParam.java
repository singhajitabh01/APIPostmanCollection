package GetAPITestsWithBDD;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class getUserPosstWithPathParam {
	
	@Test
	public void getUsersWithPathParamTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
		.header("Authorization","Bearer d7b91f9ab19da72675e24f827efa1c2eea07f35f0c73368cf96ad9928adb178a")
			.pathParam("userID", 7380155)
			.when()
			.get("/public/v2/users/{userID}/posts")
			.then().log().all()
			.assertThat()
					.statusCode(200)
					.and()
					.body("title", hasItem("Votum pauper decerno solus damno voluptates."));
		
	}
	

}
