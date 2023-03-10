package lecture14.exercises.regressionSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//Test scenario - successful follow/unfollow user
// . 0. Navigate to Home page
// 1. Navigate to Login page by clicking Login tab button
// 2. Enter correct username
// 3. Enter correct password
// 4. Click sign in button
// 5. Validate that the url is correct
// 6. Click on follow button at the first user's post
// 7. Click on unfollow button at the first user's post
// 8. Validate that a pop-up appears indicating that the user is unfollowed

public class FollowUnfollowUser {
    ChromeDriver driver;

    @BeforeMethod
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://training.skillo-bg.com:4200/posts/all");
    }

    @Test
    public void followUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebDriverWait smallWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        System.out.println("1. Navigate to Login page by clicking Login tab button");
        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        loginButton.click();

        System.out.println("2. Enter correct username");
        WebElement usernameField = driver.findElement(By.name("usernameOrEmail"));
        usernameField.sendKeys("auto_user");

        System.out.println("3. Enter correct password");
        WebElement passwordField = driver.findElement(By.cssSelector("input[formcontrolname='password']"));
        passwordField.sendKeys("auto_pass");

        System.out.println("4. Click sign in button");
        WebElement signInButton = driver.findElement(By.cssSelector("#sign-in-button"));
        signInButton.click();

        System.out.println("5. Validate that the url is correct");
        String expectedUrl = "http://training.skillo-bg.com:4200/posts/all";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        System.out.println("6. Click on follow button at the first user's post");
        WebElement followButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector((".btn.btn-primary"))));
        followButton.click();

        System.out.println("7. Click on unfollow button at the first user's post");
        WebElement unfollowButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector((".btn.btn-primary"))));
        unfollowButton.click();

        // Тук се затрудних с локализирането на "toastMsg"
        // System.out.print("8. Validate that a pop-up appears indicating that the user is unfollowed");
        // WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By........")));
        // String toastMsgText = toastMsg.getText().trim();
        // Assert.assertEquals(toastMsgText, "You unfolowed Dimitar871!");
    }

    @AfterMethod
    public void teardown() {
        driver.close();
    }


}

