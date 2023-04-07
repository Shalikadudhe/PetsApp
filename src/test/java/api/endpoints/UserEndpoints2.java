package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

/*
 * 
 * userEndpoints.java ------is created to perform CRUD operations
 */
public class UserEndpoints2 {
	// method created for getting URL's from properties file
	public static ResourceBundle getUrl() {

		ResourceBundle routes = ResourceBundle.getBundle("routes"); // load properties file
		return routes;

	}

	public static Response createUser(User payload) {

		String post_url = getUrl().getString("post_url");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(post_url);

		return response;

	}

	public static Response readUser(String userNm) {

		String get_url = getUrl().getString("get_url");
		Response response = given().pathParam("username", userNm).when().get(get_url);

		return response;

	}

	public static Response updateUser(String userNm, User payload) {

		String update_url = getUrl().getString("update_url");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", userNm)
				.body(payload).when().put(update_url);

		return response;

	}

	public static Response deleteUser(String username) {
		String delete_url = getUrl().getString("delete_url");
		Response response = given().pathParam("username", username).when().delete(delete_url);

		return response;

	}

}
