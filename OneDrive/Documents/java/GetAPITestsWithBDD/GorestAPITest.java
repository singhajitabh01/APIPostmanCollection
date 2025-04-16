package GetAPITestsWithBDD;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GorestAPITest {
	
	@Test(priority = 1)
	public void getSingleUser() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		Response response = given()
								.header("Authorization","Bearer d7b91f9ab19da72675e24f827efa1c2eea07f35f0c73368cf96ad9928adb178a")
									.when()
										.get("/public/v2/users/7825371");
		
		System.out.println("status code value is "+response.statusCode());
		System.out.println("status Line "+response.statusLine());		
		response.prettyPrint();
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		
		System.out.println("To get only the Visible text value from response ...");
		Assert.assertTrue(response.statusLine().contains("200 OK"));
		
		//fetch the JSON body
		
		JsonPath js = response.jsonPath();//remember this 
		int idValue = js.getInt("id");
		System.out.println("Get Id value from body");
		System.out.println(idValue);		
		Assert.assertEquals(idValue, 7825371);
		
		
		String nameValue = js.getString("name");
		System.out.println("Get name value from body");
		System.out.println(nameValue);	
		Assert.assertEquals(nameValue, "Dr. Sharda Kaur");
		
		String statusValue = js.getString("status");
		System.out.println("get Status value from body");
		System.out.println(statusValue);
		Assert.assertEquals(statusValue,"inactive");
		
	}
	
	@Test(priority = 2)
	public void getAllUserValue() {
		RestAssured.baseURI = "https://gorest.co.in";
		Response response = given()
			.header("Authorization","Bearer d7b91f9ab19da72675e24f827efa1c2eea07f35f0c73368cf96ad9928adb178a")
			    	.when()
			    		.get("/public/v2/users");
		
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertTrue(response.statusLine().contains("200 OK"));
		//above line are similar for both to get single and multiple user
		
		response.prettyPrint();
		
		JsonPath js = response.jsonPath();//remember this 
		
		List<Integer>idList = js.getList("id");//getList work similar as findElements
		System.out.println(idList);
		
		System.out.println("All Name list Value....");
		List<String>nameList = js.getList("name");
		System.out.println(nameList);
	}

}
