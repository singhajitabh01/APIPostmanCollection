package GetAPITestsWithBDD;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetUserPostIWithPathParamUsingDataProvidor {
	
	@DataProvider
	public Object [][] getUsersData(){
		return new Object [][] {
			{7828616,"Cognatus animus voluptatem in valde."},
			{7828620,"Deprimo demergo somnus vinco et atrox consequuntur solitudo."},
			{7828622,"Acerbitas cohibeo triduana corrigo thymum solus tersus acsi."}			
		};
	}
	
	@Test(dataProvider = "getUsersData")
	public void getUserPostWithPathParamTest(int userValue,String title) {
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
			.header("Authorization","Bearer d7b91f9ab19da72675e24f827efa1c2eea07f35f0c73368cf96ad9928adb178a")
				.pathParam("userId",userValue) 
					.when()
						.get("/public/v2/users/{userID}/posts")
							.then().log().all()
								.assertThat()
								.statusCode(200)
								.and().body("title",hasItem(title));
	}	

}
