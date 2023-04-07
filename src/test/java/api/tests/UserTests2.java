package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userpayload;
	public Logger logger;

	@BeforeClass
	public void setUpData() {

		faker = new Faker();
		userpayload = new User();

		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());

		// for logs
		logger = LogManager.getLogger(this.getClass());

	}

	@Test(priority = 1)
	public void testPostUser() {

		logger.info("****************************Creating user*******************");
		Response response = UserEndpoints2.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2)
	public void testGetUserByName() {

		logger.info("****************************Reading User BY name*******************");
		Response response = UserEndpoints2.readUser(this.userpayload.getUsername());
		response.then().log().all();
		// response.statusCode();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public void testUpadteUserByName() {

		// update data by using payload
		logger.info("****************************Updating  User by Name*******************");
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndpoints2.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().body();
		// response.statusCode();
		Assert.assertEquals(response.getStatusCode(), 200);

		// checking data after update
		Response responseAfterUpdate = UserEndpoints.readUser(this.userpayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public void testDeleteUser() {
		logger.info("****************************Deleting user*******************");
		Response response = UserEndpoints2.deleteUser(this.userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
