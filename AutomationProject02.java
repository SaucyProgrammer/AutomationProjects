package Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class AutomationProject02 {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty( "webdriver.chrome.driver", "C:\\Users\\zdarw\\OneDrive\\Documents\\webdriver_selenium_java08\\chromedriver.exe" );//Accesses the WebDriver through key and path.

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        //Open Chrome browser
        driver.get( "https://www.google.com/" );


        //Navigate to http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx
        driver.navigate().to( "http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx" );


        // Login using username Tester and password test
        driver.findElement( By.name( "ctl00$MainContent$username" ) ).sendKeys( "Tester" + Keys.TAB + "test" + Keys.ENTER );
        Thread.sleep( 1000 );




        // Click on Order link
        driver.findElement( By.linkText( "Order" ) ).click();
        Thread.sleep( 1000 );




        // Enter a random product quantity between 1 and 100
        int randNumber = ( int ) ( Math.random() * 101 );
        driver.findElement( By.id( "ctl00_MainContent_fmwOrder_txtQuantity" ) ).sendKeys( Keys.BACK_SPACE + "" + randNumber + Keys.ENTER );





        // part 1: Click on Calculate and verify that the Total value is correct.
        // Price per unit is 100.

        String verifyPrice = driver.findElement( By.id( "ctl00_MainContent_fmwOrder_txtUnitPrice" ) ).getText();
        verifyPrice.equals( "100" );




        //part 2: If-else loop to verify he discount of 8 % is applied to quantities of 10+.
        String percent = driver.findElement( By.name( "ctl00$MainContent$fmwOrder$txtDiscount" ) ).getText();

        if (percent.equals( "8" )) {
            System.out.println( "discount was applied." );
        }


        //Generate and enter random first name and last name.

        driver.findElement( By.name( "ctl00$MainContent$fmwOrder$txtName" ) ).sendKeys( generateRandomName() );



         // Generate and Enter random street address

        driver.findElement( By.id( "ctl00_MainContent_fmwOrder_TextBox2" ) ).sendKeys( "" + generateRandomStreet() );



        //Generate and Enter random city:
        driver.findElement( By.id( "ctl00_MainContent_fmwOrder_TextBox3" ) ).sendKeys( generateRandomCity() + Keys.TAB + generateRandomState());
//



        //Generate and Enter a random 5 digit zip code
        driver.findElement( By.name( "ctl00$MainContent$fmwOrder$TextBox5" ) ).sendKeys( generateRandomZip()+"" );


        //Enter and generate a random credit card:
        List<WebElement> cardType = driver.findElements(By.name( "ctl00$MainContent$fmwOrder$cardList" ));
               cardType.get((int)(Math.random()*cardType.size())).click();



          //This if-else loops validate if the correct card type is selected and then generates a random accurate card type.
          if (driver.findElement( By.xpath( "//input[@value='MasterCard']" )).isSelected()){

              long []master = {5108757581013179L, 5048373345513653L, 5048374646221004L, 5048377761573190L, 5048374052885987L};
              int randoCard = ( int ) ( Math.random() * master.length );

              driver.findElement( By.id( "ctl00_MainContent_fmwOrder_TextBox6" ) ).sendKeys("" +master[randoCard] );



          }else if (driver.findElement( By.xpath("//input[@value='Visa']"  )).isSelected()) {

              long[] visa = {4108757581013179L, 4048373345513653L, 4448374646221004L, 4548377761573190L, 4948374052885987L};
              int randoVisa = ( int ) ( Math.random() * visa.length );

              driver.findElement( By.id( "ctl00_MainContent_fmwOrder_TextBox6" ) ).sendKeys("" +visa[randoVisa] );




          }else if (driver.findElement( By.xpath( "//input[@value='American Express']" )).isSelected()) {

              long[] american = {310875758101317L, 304837334551365L, 304837464622100L, 304837776157319L, 304837405288598L};
              int randoUSA = ( int ) ( Math.random() * american.length );

              driver.findElement( By.id( "ctl00_MainContent_fmwOrder_TextBox6" ) ).sendKeys("" +american[randoUSA] );


          } else{

              System.out.println("Invalid card type");

          }


          //Select a expiration date
          driver.findElement( By.xpath( "//input[@name='ctl00$MainContent$fmwOrder$TextBox1']" ) ).sendKeys( "03/25" );
          Thread.sleep( 1000 );



          //Process the order
           driver.findElement( By.xpath( "//a[@id='ctl00_MainContent_fmwOrder_InsertButton']" ) ).click();
           Thread.sleep( 3000 );



          //check to see if the order has been processed
         WebElement processed = driver.findElement( By.xpath( "//*[contains(text(),'New order has been successfully added')]" ) );
        System.out.println(processed.getText());
           Assert.assertTrue( processed.isDisplayed() );



           //logout
           driver.findElement( By.id( "ctl00_logout" ) ).click();

            System.out.println("All Tests Completed Successfully.");







    }

    public static String generateRandomName() {
        String[] names = {"Lynnelle Capelin", "Bili Jenoure", "Hillyer Roostan", "Felicle Raftery", "Jeana Jaqueminet"
                , "Damian Onslow", "Kerrie de Wilde ", "Angelica Kisby", "Eldridge Millis ", "Risa Ludvigsen", "Annmaria Dawbury ", "Larisa Dineen ", "Johny Braham", "John Doe", "Jane Doe"};

        int randomName = ( int ) ( Math.random() * names.length );

        return names[randomName];

    }

    public static String generateRandomStreet() {
        String[] street = {"2 Shopko Court", "08 Westport Parkway", "9085 Cambridge Trail", "185 Corscot Crossing", "9 Straubel Pass", "91033 Fairview Park", "2 Sullivan Alley", "1 Stone Corner Place",
                "51779 Truax Street", "05 Merry Terrace",
                "1957 Sachtjen Park",
                "08 Superior Junction",
                "479 Dahle Plaza",
                "46803 Badeau Street"
        };

        int randomST = ( int ) ( Math.random() * street.length );

        return street[randomST];

    }

    public static String generateRandomState() {
        String[] names = {"Virginia", "Texas", "California", "Kansas", "Arkansas", "Utah", "West Virginia", "Puerto Rico", "Georgia", "Delaware"
                , "Louisiana", "Ohio", "Mississippi", "NewYork", "Pennsylvania", "Maryland", "Washington DC", "Oregon", "Alaska", "Idaho"};

        int randomName = ( int ) ( Math.random() * names.length );

        return names[randomName];

    }

    public static String generateRandomCity() {
        String[] names = {"London", "Fairfax", "Alexandria", "Falls church", "Merrifield", "Chantilly", "Mclean", "Loudon", "Reston", "Hernondon"
                , "CharlottesVille", "Leesburg", "Ashburn", "Fairfax city", "Falls church city", "Gainesville"};

        int randomName = ( int ) ( Math.random() * names.length );

        return names[randomName];

    }

    public static int generateRandomZip() {
        int[] zip = {22079, 35462, 95636, 64852, 22012, 22200, 20009, 22101, 22109, 22315, 89766, 97865, 35432, 68564, 74315};

        int randomName = ( int ) ( Math.random() * zip.length );

        return zip[randomName];

    }

//    public static long generateRandomCard() {
//        long []master = {5108757581013179L, 5048373345513653L, 5048374646221004L, 5048377761573190L, 5048374052885987L};
//        long[] visa = {4108757581013179L, 4048373345513653L, 4448374646221004L, 4548377761573190L, 4948374052885987L};
//        long[] american = {310875758101317L, 304837334551365L, 304837464622100L, 304837776157319L, 304837405288598L};
//
//
//
//
//
//
//
//
//
//
//    }

}

