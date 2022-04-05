import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class PracticeProjectBookingAFlight {

    /** This project is to practice and simulate booking a flight on Booking.com
     *
     * Zakeria Darweschzad
     * 04/03/2022
     *
     * completed on 04/05/2022
     *
     * **/
    public static void main(String[] args) throws InterruptedException {
        System.setProperty( "webdriver.chrome.driver", "C:\\Users\\zdarw\\OneDrive\\Documents\\webdriver_selenium_java08\\chromedriver.exe" );
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(5));
        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor ) driver;



        //Navigate to Booking.com
        driver.get( "https://booking.com/" );




        //Then navigate to the Flights webpage:
        driver.findElement( By.xpath("//a[@data-decider-header='flights']") ).click();




        //Then verify that your trip type is "One-way" is clicked:
        WebElement oneWay =  driver.findElement( By.xpath( " //div[contains(text(),'One-way')]") );
        driver.findElement( By.xpath( "//div[@data-testid='searchbox_controller_trip_type_ONEWAY']") ).click();

        System.out.println("Is \"One-way\" selected? "+oneWay.isDisplayed());




        //Then Select the option : "First class"
        new Select( driver.findElement(By.xpath( "//select[@class='css-1k0jlfl']" )) ).selectByValue( "FIRST" );
        System.out.println();




        //verify that seating options are Economy, Premium economy, Business, and First Class.
        List <WebElement> actual = new Select( driver.findElement(By.xpath( "//select[@class='css-1k0jlfl']" ))).getOptions();

        for (WebElement element : actual) {
            System.out.print( element.getText() + ", ");
        }
        System.out.println();




        //Then select departure location and your destination:

        //Part1: type in and click on IAD
        driver.findElement( By.xpath( "//input[@placeholder='Where from?']" ) ).click();

        Thread.sleep( 1000 );
        driver.findElement( By.xpath( "//div[@class='css-526oso']//input[@placeholder='Where from?']" ) ).sendKeys( "IAD" );

        Thread.sleep( 1000 );
        driver.findElement( By.xpath( "//div[@class='css-1lc7njo']//div[@class='css-2r1cd1']" ) ).click();




        //Part2: type in and click on CAS
        driver.findElement( By.xpath( "//input[@placeholder='Where to?']" ) ).click();

        Thread.sleep( 1000 );
        driver.findElement( By.xpath( "//div[@class='css-526oso']//input[@placeholder='Where to?']" ) ).sendKeys( "CAS" );

        Thread.sleep( 1000 );
        driver.findElement( By.xpath( "//div[@class='css-1lc7njo']//div[@class='css-2r1cd1']" ) ).click();
        Thread.sleep( 1000 );



        //Then book your flight for june 23, 2022.
        driver.findElement( By.xpath( "//input[@placeholder='Depart']" ) ).click();
        driver.findElement( By.xpath( "//button[starts-with(@class,'Calendar-module__control')]" ) ).click();
        Thread.sleep( 1000 );

        js.executeScript("scrollBy(0,400)" );




        Thread.sleep( 4000 );
        WebElement date = driver.findElement( By.xpath( "//*[@aria-labelledby='__bui-652022']//thead//following-sibling::tbody//tr[4]//td[5]//span" ) );
        System.out.println(date.isDisplayed());
        date.click();


        //Click on search:
        driver.findElement( By.xpath( "//button[@data-testid='searchbox_submit']" ) ).click();






       //Then scroll down page to see all options
        js.executeScript("scrollBy(0,1500)" );
        Thread.sleep( 5000 );

      //Then close your program
        driver.quit();
        System.out.println("All tests completed successfully.");


    }
}
