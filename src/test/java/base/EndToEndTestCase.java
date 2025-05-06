package base;

import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductOnePage;
import Pages.ProductTwoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTestCase extends BaseSteps{
   @Test
    public void testEndToEnd() {
      LoginPage loginPage= homePage.clickonLoginlink();
     loginPage.insertUserName("Monad");
     loginPage.insertPassword("monad1234");
     loginPage.clickonloginbutton();
       String actualResult = loginPage.getverificationofuser().toLowerCase().trim();
       String expectedResult = "welcome monad";
       Assert.assertEquals(actualResult, expectedResult, "Login verification failed!");


    }
    @Test(priority = 2)
    public void TestProductOne(){
       ProductOnePage productonePage=homePage.clickonProductOne();
       productonePage.clickAddToCart();
      String actualResult= productonePage.alertText();
      productonePage.alertClick();
      String expectedResult="Product added.";
      Assert.assertEquals(actualResult,expectedResult);
      productonePage.clickhomebutton();


   }
    @Test(priority = 3)
    public void TestProductTwo(){
        ProductTwoPage productTwoPage =homePage.clickonProductTwo();
       productTwoPage.clickAddToCart();
        String actualResult= productTwoPage.alertText();
        productTwoPage.alertClick();
        String expectedResult="Product added.";
        Assert.assertEquals(actualResult,expectedResult);
      productTwoPage.clickhomebutton();

    }
    @Test(priority = 4)
    public void testCartValidationAndCheckout() throws InterruptedException {
        CartPage cartPage = homePage.clickonCart();

        // Wait for 2 seconds to ensure cart items are loaded
        Thread.sleep(2000);

        // Step 1: Validate Cart - Ensure both products are in the cart
        String expectedTitle1 = "Samsung galaxy s6";
        String expectedPrice1 = "360";
        String expectedTitle2 = "Nexus 6";
        String expectedPrice2 = "650";

        // Log actual values for debugging
        String actualTitle1 = cartPage.getCartItemTitle(0).trim();  // Corrected index
        String actualTitle2 = cartPage.getCartItemTitle(1).trim();  // Corrected index
        String actualPrice1 = cartPage.getCartItemPrice(0).trim();  // Corrected index
        String actualPrice2 = cartPage.getCartItemPrice(1).trim();  // Corrected index

        System.out.println("Actual Item 1: " + actualTitle1);
        System.out.println("Actual Price 1: " + actualPrice1);
        System.out.println("Actual Item 2: " + actualTitle2);
        System.out.println("Actual Price 2: " + actualPrice2);


        // Validate Cart Items
        boolean itemsAreCorrect = cartPage.validateCartItems(expectedTitle1, expectedTitle2, expectedPrice1, expectedPrice2);
        Assert.assertTrue(itemsAreCorrect, "Cart items are incorrect!");

        // Step 2: Verify Total - Ensure the total amount is correct
        String expectedTotal = "1010";
        boolean totalIsCorrect = cartPage.verifyTotal(expectedTotal);
        Assert.assertTrue(totalIsCorrect, "Total amount is not correct!");

        // Step 3: Proceed to Checkout - Complete the purchase
        boolean checkoutSuccess = cartPage.completeCheckout("Monad", "Egypt", "Cairo", "1234567", "May", "2025");
        Assert.assertTrue(checkoutSuccess, "Thank you for your purchase!");
    }
}