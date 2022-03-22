package Assignments;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationProject01 {
    public static void main(String[] args) throws InterruptedException {

        /** Note: at anytime if the tests are conducted there is a Exception or Error message for using the same username, and email...
         * it must be changed to a unique one to have access. Zakeria Darweschzad **/

        System.setProperty("webdriver.chrome.driver","C:\\Users\\zdarw\\OneDrive\\Documents\\webdriver_selenium_java08\\chromedriver.exe"  );
        WebDriver driver = new ChromeDriver();


        // Navigate to http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?

        driver.get( " http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?" );


        //Verify the the title is "Welcome to Duotify!"
        String currentTitle = driver.getTitle();
        String ExpectedTitle = "Welcome to Duotify!";

        if (currentTitle.matches( ExpectedTitle )) {
            System.out.println( "Test passed: title matches." );
        }else {
            System.out.println("Test failed.");
            System.exit(-1)
        }

        //Click on Signup here
        driver.findElement( By.id( "hideLogin" ) ).click();
        System.out.println("click on sign up successful.");




       // Fill out the form with the required info
        driver.findElement( By.name( "username" ) ).sendKeys( "zdark003"+Keys.TAB+ "Zakeria"+Keys.TAB+"Darweschzad");

        driver.findElement( By.name( "email" ) ).sendKeys( "zdark003@gmail.com" +Keys.TAB+"zdark002@gmail.com" );

        driver.findElement( By.id( "password" )).sendKeys( "password0123"+Keys.TAB+"password0123" );

        Thread.sleep( 1000 );




        // Click on Sign up
        driver.findElement( By.name( "registerButton" ) ).click();

        System.out.println("Registration successful.");





        // Once logged in to the application, verify that the URL is:
        //http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?

        String currentUrl = driver.getCurrentUrl();


        if (currentUrl.contains( "http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?" )) {
            System.out.println( "Test passed: url matches." );
        }else {
            System.out.println("Test failed.");
            System.exit(-1)
        }



        //Click on the username on the left navigation bar and then click logout.
         driver.findElement( By.id( "nameFirstAndLast" ) ).click();
        Thread.sleep( 1000 );
        driver.findElement(By.xpath("//*[@id='rafael']")).click();




        //verify logout link:


        Thread.sleep( 1000 );
        if (driver.getCurrentUrl().equalsIgnoreCase( "http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php" )) {
            System.out.println( "Test passed: logout successful." );
        }else {
            System.out.println("Test failed.");
            System.exit(-1)
        }


        //login once again
        driver.findElement(By.id("loginUsername")).sendKeys("zdark003");
        driver.findElement(By.id("loginPassword")).sendKeys("password0123");
        driver.findElement( By.name( "loginButton" ) ).click();
        System.out.println("Login back in successful.");
        Thread.sleep( 1000 );



        // Verify successful login by verifying that the home page contains the text "You Might Also Like".


        if (driver.getPageSource().contains( "You Might Also Like"  ) ) {
            System.out.println("Test passed: login successful.");

        }else{
            System.out.println("Test failed.");
            System.exit( -1 );
        }
        driver.quit();

        System.out.println("All tests passed successfully.");

    }
}
