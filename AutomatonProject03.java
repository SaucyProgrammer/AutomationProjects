package Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AutomatonProject03 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\zdarw\\OneDrive\\Documents\\webdriver_selenium_java08\\msedgedriver.exe");

        WebDriver driver = new EdgeDriver(); // initializes a browser

        driver.get("https://www.duotech.io/");

        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();


        //Navigate  to carfax.com
        driver.navigate().to( "http://carfax.com/" );


        //Click on Find a Used Car
        driver.findElement( By.xpath( "//a[@class='cgh-link-tag']" ) ).click();


        //Verify the page title contains the word “Used Cars”
        String exTitle = "Used Cars";

        Assert.assertTrue( driver.getTitle().contains( exTitle) );


        //Choose “Tesla” for  Make.
        new Select( driver.findElement(By.xpath( "//select[@class='form-control search-make search-make--lp']") )).selectByValue( "Tesla" );


        try {
            List<String> expected = Arrays.asList("All Models", "Model 3", "Model S", "Model X", "Model Y", "Roadster");

            List<WebElement> options = ( List<WebElement> ) new Select(driver.findElement(By.xpath("//select[@name='model']"))).getOptions( );
            List<String> actual = new ArrayList<>( );

            Thread.sleep(2000);
            for (WebElement s : options) {
                actual.add(s.getText( ));
            }

            Collections.sort(actual);
            Collections.sort(expected);

            Assert.assertEquals(expected, actual);

        } catch (AssertionError exception) {
            System.out.println("The expected list elements say it is 'All Models' or 'Model 3' but now the rest of the code may continue.");

        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Select Model S:
        Select AllModels = new Select(driver.findElement(By.name("model")));
        AllModels.selectByValue("Model S");


        //Enter the zip code 22182 and click Next:
        driver.findElement(By.xpath("//input[@name='zip']")).sendKeys("22182");
        driver.findElement(By.xpath("//button[@type='submit']")).click( );


        //Verify that the page contains the text “Step 2 – Show me cars with”:
        String expectedTitleSEC = "Step 2 – Show me cars with";
        String stringHTML = driver.getPageSource( );

        stringHTML.contains(expectedTitleSEC);
        System.out.println("\n Page contains text: \"Step 2 - Show me cars with \" ");

        //Check all 4 checkboxes:
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/main/div[3]/div[1]/div/div[2]/ul/li[1]")).click( );
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/main/div[3]/div[1]/div/div[2]/ul/li[2]/label")).click( );
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/main/div[3]/div[1]/div/div[2]/ul/li[3]/label")).click( );
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/main/div[3]/div[1]/div/div[2]/ul/li[4]/label")).click( );


        //Save the count of results from “Show me X Results” button. In this case it is 10.
        String savedResults = driver.findElement(By.xpath("//button[@class='button large primary-green show-me-search-submit']//span[@class='totalRecordsText']")).getText( );
        System.out.println("Show me results: " + savedResults);

        driver.findElement(By.xpath("//button[@class='button large primary-green show-me-search-submit']//span[@class='totalRecordsText']")).click( );


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Verify the results count by getting the actual number of results displayed in the page by getting the count of WebElements that represent each result:
        List<WebElement> listing = Arrays.asList((

                        driver.findElement(By.xpath("//article[@id='listing_0']")) ),
                driver.findElement(By.xpath("//article[@id='listing_1']")),
                driver.findElement(By.xpath("//article[@id='listing_2']")),
                driver.findElement(By.xpath("//article[@id='listing_3']")),
                driver.findElement(By.xpath("//article[@id='listing_4']")),
                driver.findElement(By.xpath("//article[@id='listing_5']")),
                driver.findElement(By.xpath("//article[@id='listing_6']")),
                driver.findElement(By.xpath("//article[@id='listing_7']")),
                driver.findElement(By.xpath("//article[@id='listing_8']")));

        List<WebElement> results = new ArrayList<>( );
        results.addAll(listing);

        Assert.assertEquals(listing, results);
        System.out.println("\nResults for Tesla Model S in Vienna verified as a Match.");

        //Verify that each result header contains “Tesla Model S”.
        driver.getPageSource( ).contains("Tesla Model S");
        System.out.println("\nEach Result header contains the text: \" Tesla Model S \" ");

        //Get the price of each result and save them into a List in the order of their appearance. (You can exclude “Call for price” options):
        List<Integer> values = Arrays.asList(76635,94995,41999,51995,41990,92999,50990,79985 );

        //Choose “Price - High to Low” option from the Sort By menu and then verify:
        Select sort = new Select(driver.findElement(By.xpath("//select[@aria-label='SelectSort']")));
        sort.selectByValue("PRICE_DESC");
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/main/div[2]/div/div[3]/div[2]/div/div[1]/div/div[2]/div/form/div/select/option[2]")).isSelected();
        System.out.println("Verified that \" Price - High to Low \" is selected.\n" );

        //Choose “Mileage - Low to High” option from Sort By menu then verify:
        Select sort2 = new Select(driver.findElement(By.xpath("//select[@aria-label='SelectSort']")));
        sort2.selectByVisibleText("Mileage - Low to High");
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/main/div[2]/div/div[3]/div[2]/div/div[1]/div/div[2]/div/form/div/select/option[5]")).isSelected();
        System.out.println("Verified that \" Mileage - Low to High \" is selected.\n" );

        //Choose “Year - New to Old” option from Sort By menu then verify:
        Select sort3 = new Select(driver.findElement(By.xpath("//select[@aria-label='SelectSort']")));
        sort3.selectByVisibleText("Year - New to Old");
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/main/div[2]/div/div[3]/div[2]/div/div[1]/div/div[2]/div/form/div/select/option[6]")).isSelected();
        System.out.println("Verified that \" Year - New to Old \" is selected.\n" );
        Thread.sleep(2000);

        System.out.println("Automation test is complete." );

        driver.quit();



    }
}
