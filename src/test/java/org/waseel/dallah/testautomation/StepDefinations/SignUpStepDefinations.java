package org.waseel.dallah.testautomation.StepDefinations;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Assert;
import org.waseel.dallah.testautomation.AndroidBaseTest;
import org.waseel.dallah.testautomation.pageobjects.FirstSignUpScreen;
import org.waseel.dallah.testautomation.pageobjects.LandingScreen;
import org.waseel.dallah.testautomation.pageobjects.SecondSignUpScreen;
import org.waseel.dallah.testautomation.pageobjects.SignInScreen;
import org.waseel.dallah.testautomation.pageobjects.SignUpSucessPopup;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class SignUpStepDefinations extends AndroidBaseTest{

	private LandingScreen landingScreen;
	private SignInScreen signInScreen;
	private FirstSignUpScreen firstSignUpScreen;
	private SecondSignUpScreen secondSignUpScreen;
	private SignUpSucessPopup signUpSucessPopup;
	
	Properties  properties;
	FileInputStream fileInputStream;
	
	String National_IdIqama_ID_number = null; 
	String Full_Name = null;
	String Email = null;
	String Phone_number= null;
	String Nationality = null;
	String Date_of_Birth = null;
	String Password = null;

	@Given("I open the Dawy app")
	public void I_open_the_Dawy_app() throws Exception
	{
		System.out.println("first step defination method");
		setUp();
		
		properties = new Properties();
		fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\TestDataFiles\\SignUpTestdata.properties");
		properties.load(fileInputStream);

		
		National_IdIqama_ID_number = properties.getProperty("National_Id/Iqama_ID_number"); 
		Full_Name = properties.getProperty("Full_Name");
		Email = properties.getProperty("Email");
		Phone_number= properties.getProperty("Phone_number");
		Nationality = properties.getProperty("Nationality");
		Date_of_Birth = properties.getProperty("Date_of_Birth");
		Password = properties.getProperty("Password");
		
	}

	@And("i am on the landing screen")
	public void i_am_on_the_landing_screen()
	{
		landingScreen = dontAllowAccessToLocationAndReturnLandingScreen();
	}

	@When("i tap on the profile icon in landing page")
	public void i_tap_on_the_profile_icon_in_landing_page()
	{
		signInScreen = landingScreen.navigateToSignInScreenAndReturnSignInScreen();
	}

	@Then("i am taken to the Sign in screen where i can see a link as Register Now")
	public void i_am_taken_to_the_Sign_in_screen_where_i_can_see_a_link_as_Register_Now()
	{
		boolean isRegister_nowLinkVisible =  signInScreen.isRegister_nowLinkVisible();
		Assert.assertTrue(isRegister_nowLinkVisible, "oops....Register now link is not available");
	}

	@When("i tap on the Register now link")
	public void i_tap_on_the_Register_now_link()
	{
		firstSignUpScreen = signInScreen.navigateToSignUpScreenAndReturnFirstSignUpScreen();
	}
	
	@Then("i am taken to the First Signup screen where i can see a text field to enter ID")
	public void i_am_taken_to_the_First_Signup_screen_where_i_can_see_a_text_field_to_enter_ID()
	{
		boolean isNationIdORIqamaNumberTextFieldDisplayed = false;
		isNationIdORIqamaNumberTextFieldDisplayed = firstSignUpScreen.isNationIdORIqamaNumberTextFieldDisplayed();
		Assert.assertTrue(isNationIdORIqamaNumberTextFieldDisplayed, "oops....NationIdORIqamaNumber TextField is not Displayed");
	}
	
	@And("the ID text field is prefilled with {string}")
	public void the_id_text_field_is_prefilled_with(String id_prefeill) {
		String NationIdORIqamaNumber = null;
		NationIdORIqamaNumber = firstSignUpScreen.getNationIdORIqamaNumber();
		Assert.assertEquals(NationIdORIqamaNumber,id_prefeill,"oops.... Expected :: "+id_prefeill+" but enountered :: "+NationIdORIqamaNumber);
	}
	
	@And("a next button should be available")
	public void a_next_button_should_be_available()
	{
		boolean isNextButtonAvailable = firstSignUpScreen.isNextButtonAvailable();
		Assert.assertEquals(isNextButtonAvailable, true,"oops.... Next button isn't available");
		
	}
	
	@When("i enter National Id\\/Iqama")
	public void i_enter_National_Id_or_Iqama()
	{
		firstSignUpScreen.setNationIdORIqamaNumber(National_IdIqama_ID_number);
	}
	
	@And("tap on Next button")
	public void tap_on_Next_button()
	{
		secondSignUpScreen = firstSignUpScreen.clickNextAndProceedToSecondSignUpScreenAndReturnSecondSignUpScreen();
	}
	
	@Then("i am taken to Second Sign Up screen")
	public void i_am_taken_to_Second_Sign_Up_screen()
	{
		boolean isControlOnSecondSignUpScreen = false;
		isControlOnSecondSignUpScreen = secondSignUpScreen.isControlOnSecondSignUpScreen();
		Assert.assertTrue(isControlOnSecondSignUpScreen,"oops... control doesn't seem to be in SecondSignUpScreen");
	}
	
	@When("i enter Full_Name {string}")
	public void i_enter_Full_Name(String fullName)
	{
		secondSignUpScreen.setFull_Name(Full_Name);
	}
	
    @And("i enter Email {string}")
    public void i_enter_Email(String email)
    {
    	secondSignUpScreen.setEmail(Email);
    }
    
     @And("i enter Phone_Number {string}")
     public void i_enter_Phone_Number(String phoneNumber)
     {
    	 secondSignUpScreen.setPhoneNumber(Phone_number);
     }
     
     @And("i select Nationality {string}")
     public void i_select_Nationality(String nationality)
     {
    	 secondSignUpScreen.setNationality(Nationality);
     }
     
     @And("i select Date_of_birth {string}")
     public void i_select_Date_of_birth(String dateofbirth)  throws Exception 
     {
    	 secondSignUpScreen.setDate_of_Birth(Date_of_Birth);
     }
    
     @And("i enter password {string}")
     public void i_enter_password(String password)
     {
    	 secondSignUpScreen.setPassword(Password);
     }
    
     @And("i hit SignUp button")
     public void i_hit_SignUp_button()
     {
    	 secondSignUpScreen.clickOnSignUpButton();
     }
    
     @Then("i Should see a popup with a Success Message")
     public void i_Should_see_a_popup_with_a_Success_Message()
     {
    	 signUpSucessPopup = new SignUpSucessPopup(androidDriver);
    	 String Success_Message = null;
    	 Success_Message = signUpSucessPopup.getSuccessMessageFromPopup();
    	 String Expected_Success_Message = "Signup happened successfully"; 
    	 Assert.assertEquals(Success_Message, Expected_Success_Message,"oops... encountered success message is :: "+Success_Message+"  Expected success message is :: "+Expected_Success_Message);
     }
     
     @And("click on ok button")
     public void click_on_ok_button()
     {
    	 signUpSucessPopup.clickOkButton();
     }
}
