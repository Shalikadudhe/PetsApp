package api.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userID, String userName, String fname, String lname, String userEmail, String pwd,String ph) {

		User userpayload = new User();

		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(userName);
		userpayload.setFirstName(fname);
		userpayload.setLastName(lname);
		userpayload.setEmail(userEmail);
		userpayload.setPassword(pwd);
		userpayload.setPhone(ph);

		Response response = UserEndpoints.createUser(userpayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void deleteUserByName(String userName) {

		Response response = UserEndpoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);

	}

/*	@Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProvider.class)
	public void readUserByName(String username) {
		Response response = UserEndpoints.readUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 4, dataProvider = "UserNames", dataProviderClass = DataProvider.class)
	public void updateUserByName(String username, User userpayload) {
		Response response = UserEndpoints.updateUser(username, userpayload);
		Assert.assertEquals(response.getStatusCode(), 200);

	}
*/
}
