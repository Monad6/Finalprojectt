package base;

import Pages.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTestCase extends BaseSteps {

    @Test
    public void SignUpTest() throws InterruptedException {
        SignUpPage signupPage = homePage.clickonSignUplink();

        signupPage.enterUsername("Monadkhaled");
        signupPage.enterPassword("monad123");
        signupPage.clickSignUpButton();

        Thread.sleep(1000); // Optional: Replace with proper wait for alert

        String actualResult = signupPage.alertText();
        signupPage.alertClick();

        String expectedResult = "Sign up successful."; // Adjust if needed
        Assert.assertTrue(actualResult.contains(expectedResult));
    }
}
