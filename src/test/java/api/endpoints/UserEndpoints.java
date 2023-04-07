package api.endpoints;
import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.response.Response;
import io.restassured.http.ContentType;


/*
 * 
 * userEndpoints.java ------is created to perform CRUD operations
 */
public class UserEndpoints {
	
	
	
	public static Response createUser(User payload) {

    Response response=	given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.post_url);
    
		return response;
		
	}

	
	public static Response readUser(String userNm) {

	    Response response=	given()
			.pathParam("username", userNm)
			.when()
			.get(Routes.get_url);
	    
			return response;
			
		}
	
	public static Response updateUser(String userNm ,User payload) {

	    Response response=	given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username",userNm)
			.body(payload)
			.when()
			.put(Routes.update_url);
	    
			return response;
			
		}
	
	public static Response deleteUser(String username) {

	    Response response=	given()
			.pathParam("username", username)
			.when()
			.delete(Routes.delete_url);
	    
			return response;
			
		}
	

}
