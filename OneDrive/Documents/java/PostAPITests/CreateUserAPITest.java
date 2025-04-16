package PostAPITests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserAPITest {
	
	
	//to create random email id (uniquiemail id will create)
	public String getRandomEmailId() {
		return "apiautomation"+ System.currentTimeMillis()+"@opencart.com";
	}
		
	String randomEmail = getRandomEmailId();
	@Test
	public void createUserWithJsonString() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.header("Authorization","Bearer d7b91f9ab19da72675e24f827efa1c2eea07f35f0c73368cf96ad9928adb178a")
				.contentType(ContentType.JSON)
					.body("{\r\n"
							+ "    \"name\": \"Akhil Singh\",\r\n"
							+ "    \"gender\": \"male\",\r\n"
							+ "    \"email\": \""+ randomEmail +"\",\r\n"
							+ "    \"status\": \"active\"\r\n"
							+ "}")
					.when()
						.post("/public/v2/users")
							.then().log().all()
							.assertThat()
							.statusCode(201);
	}
	
	//here i pass body from json file 
	@Test
	public void createUserWithJsonFilePath() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given()
			.header("Authorization","Bearer d7b91f9ab19da72675e24f827efa1c2eea07f35f0c73368cf96ad9928adb178a")
				.contentType(ContentType.JSON)
					.body(new File("./src/test/resource/Json/user.json"))//json file present in this project path
						.when().log().all()
							.post("/public/v2/users")
								.then().log().all()
									.assertThat()
										.statusCode(201);			
	}	
	
	@Test	
	public void createUserWithJsonFileWithStringReplacementTest() throws IOException {
	    RestAssured.baseURI = "https://gorest.co.in";

	    String emailID = getRandomEmailId();

	    // Read and modify the JSON
	    String rawJson = new String(Files.readAllBytes(Paths.get("./src/test/resource/Json/user.json")));
	    String updatedJson = rawJson.replace("{{email}}", emailID);

	    given().log().all()
	        .header("Authorization", "Bearer d7b91f9ab19da72675e24f827efa1c2eea07f35f0c73368cf96ad9928adb178a")
	        .contentType(ContentType.JSON)
	        .body(updatedJson) 
	    .when().log().all()
	        .post("/public/v2/users")
	    .then().log().all()
	        .assertThat()
	        .statusCode(201);
	}
	
	@Test
	public void createUsrWithStrngReplacementFetchingIDTest() throws IOException {
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailID = getRandomEmailId();
		
		String rawJson = new String(Files.readAllBytes(Paths.get("./src/test/resource/Json/user.json")));
		String updatedJson = rawJson.replace("{{email}}", emailID);
		
		int userID = given()
			.header("Authorization","Bearer d7b91f9ab19da72675e24f827efa1c2eea07f35f0c73368cf96ad9928adb178a")
			.contentType(ContentType.JSON)
				.body(updatedJson)
				.when().log().all()
				.post("/public/v2/users")
				.then().log().all()
				.assertThat()
				.statusCode(201)
				.extract().path("id");
		System.out.println("------------------------");
		System.out.println(userID);
	}

}
