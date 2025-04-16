package GetAPITestsWithBDD;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ProductAPITest {

	@Test
	public void getProductsTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response = given()
			.when()
				.get("/products");
		System.out.println("Print all response value"+response.statusCode());
		
		response.prettyPrint();
		
		JsonPath js = response.jsonPath();
		List<Integer>idValue = js.getList("id");
		System.out.println("All Id Value is "+idValue);
		
		List<Double>priceValue = js.getList("price");
		System.out.println("All Price value ==> "+priceValue);
		
		List<Double>ratingValue = js.getList("rating.rate");
		System.out.println("All Rating Value ==> "+ratingValue);
		
		List<Integer>countValue = js.getList("rating.count");
		System.out.println("All Count Value ==> "+countValue);
		
		for(int i = 0;i<idValue.size();i++) {
			int id = idValue.get(i);
			Object prices =  priceValue.get(i);//as if different type of int value is there
			ratingValue.get(i);
			countValue.get(i);
			System.out.println("ID value =>" + id + "Price value => " + prices +"Rating value => " +ratingValue +"Count Value is =>"+countValue);
		}
	}
	
	@Test
	public void getProductCountTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		given()
			.when()
				.get("/products")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.body("$.size()", equalTo(20));
		
	}

}
