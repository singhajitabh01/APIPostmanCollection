package GetAPITestsWithBDD;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetUserWithQueryParam {
	@Test
	public void getUserTestWithQueryParam() {
		RestAssured.baseURI = "https://gorest.co.in";		
		given().log().all()
			.header("Authorization","Bearer d7b91f9ab19da72675e24f827efa1c2eea07f35f0c73368cf96ad9928adb178a")
			.queryParam("name", "naveen")
				.queryParam("status", "active")
				.when()
				.get("/public/v2/users")
				.then().log().all()
				.assertThat()
					.statusCode(200)
						.and()
							.contentType(ContentType.JSON);
				
	}
	
	@Test
	public void getUserTestWithQueryParamUsingHashMap() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		Map<String, String>userQueryParam = new HashMap<String, String>();
		userQueryParam.put("name", "Akhilesh");
		userQueryParam.put("status", "active");
		
		given().log().all()
		.header("Authorization","Bearer d7b91f9ab19da72675e24f827efa1c2eea07f35f0c73368cf96ad9928adb178a")
		.queryParams(userQueryParam)//here we need to store in queryParams if store value in hashmap
			.when()
				.get("/public/v2/users")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.contentType(ContentType.JSON);
			
	}

}
