import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {

    WebDriver driver;
    String BASE_URL="https://www.instagram.com/";

    By usernameLocator= new By.ByCssSelector("input[name='username']");
    By passwordLocator= new By.ByCssSelector("input[name='password']");
    By loginButtonLocator=new By.ByCssSelector("button[type='submit']");
    By instagramLocator=By.className("s4Iyt");
    By profileNameLocator=By.className("XBGH5");
    By postLocator=By.className("_9AhH0");
    By likeButtonLocator=new By.ByCssSelector("span.fr66n");
    By postCountLocator=By.className("g47SY");
    By htmlTag=By.tagName("html");







    public App(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    public void loginWith(String username,String password){

          waitFor(usernameLocator);
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();


    }

    public void navigateToProfile(String profileName){

        waitFor(instagramLocator);
        driver.navigate().to(BASE_URL.concat(profileName));

    }

    public void clickFirsPost(){

       waitFor(profileNameLocator);

        driver.findElements(postLocator).get(0).click();

    }

    public void likeAllPost() {

        int deneme=10,count2=getPostCount();

        while (count2>0){

            waitFor(likeButtonLocator);
            driver.findElement(likeButtonLocator).click();
            driver.findElement(htmlTag).sendKeys(Keys.ARROW_RIGHT);

            count2--;
        }






    }

    private int getPostCount(){
        String count=driver.findElement(postCountLocator).getText();
        return Integer.parseInt(count);

    }


    private void waitFor(By locator){
        WebDriverWait wait=new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
