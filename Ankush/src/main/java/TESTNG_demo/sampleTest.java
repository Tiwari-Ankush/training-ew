package TESTNG_demo;

import org.testng.annotations.Test;

public class sampleTest {
	@Test (priority=-1)
	public void appOpen() {
		System.out.println("app opens...");
	}
	
	@Test(priority=0)
	public void login() {
		System.out.println("login successful...");
	}
	
	@Test (priority=1)
	public void atestFunct1() {
		System.out.println("functionality tested ok...");
	}
	
	@Test (priority=2)
	public void logout() {
		System.out.println("logout successful...");
	}
	
	@Test (priority=3)
	public void closeApp() {
		System.out.println("app closed successful....");
	}
}
