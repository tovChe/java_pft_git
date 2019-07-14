package ru.pft.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {

	
	@Test
	
	public void testRegistration() {
		app.registration().start("user", "user1@localhost.localdomain");
		
	}
	
}
