package base;

import Pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseSteps {
    public WebDriver driver;
    public HomePage homePage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //ban2el l driver mn l basesteps lel homepage
        homePage = new HomePage(driver);
        driver.manage().window().maximize();
        driver.get("https://demoblaze.com/");
    }
    @AfterClass
    public void close() {
        driver.quit();}
}
