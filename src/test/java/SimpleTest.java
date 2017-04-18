import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.fail;

/**
 * Created by lenovo on 16.04.2017.
 */
public class SimpleTest {

    @Test
    public void NavigateToMyWebSite(){
      //  System.setProperty("webdriver.gecko.driver", "/Users/lenovo/IdeaProjects/Maven1/geckodriver");
        WebDriver driver = new FirefoxDriver();

        System.out.println("Go to google page");
        driver.get("http://www.google.com/ncr");

        System.out.println("Search selenium");
        WebElement searchString = driver.findElement(By.name("q"));
        searchString.clear();
        searchString.sendKeys("selenium");

        WebElement searchButton = driver.findElement(By.name("btnG"));
        searchButton.click();

        (new	WebDriverWait(driver,	10)).until(new	ExpectedCondition<Boolean>()	{
            public	Boolean	apply(WebDriver	d)	{
                return	d.getTitle().toLowerCase().startsWith("selenium");
            }
        });

        System.out.println("Check first result");

        (new	WebDriverWait(driver,	10)).until(new	ExpectedCondition<Boolean>()	{
            public	Boolean	apply(WebDriver	d)	{ return CheckFirstLink(d); }
        });

        System.out.println("Look for Images");

        WebElement imageBtn = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver	d)	{
                WebElement filterPanel = d.findElement(By.id("hdtb-msb-vis"));
                WebElement filterImage = filterPanel.findElement(By.linkText("Images"));
                return filterImage;
            }
        });

        System.out.println("Go to Images");

        String url = imageBtn.getAttribute("href");
        driver.navigate().to(url);

        System.out.println("Check first image");

        (new	WebDriverWait(driver,	10)).until(new	ExpectedCondition<Boolean>()	{
            public	Boolean	apply(WebDriver	d)	{
                WebElement searchResultPanel = d.findElement(By.id("ires"));
                List<WebElement> linkElements = searchResultPanel.findElements(By.tagName("a"));
                return !linkElements.isEmpty()
                        && linkElements.get(0).getAttribute("href").contains("www.seleniumhq.org");
            }
        });

        System.out.println("Go back to All");

        driver.navigate().back();

        System.out.println("Check first result");

        (new	WebDriverWait(driver,	10)).until(new	ExpectedCondition<Boolean>()	{
            public	Boolean	apply(WebDriver	d)	{ return CheckFirstLink(d); }
        });

        System.out.println("Finish");

        driver.close();
    }

    public Boolean CheckFirstLink(WebDriver d){
        WebElement searchResultPanel = d.findElement(By.className("srg"));
        List<WebElement> linkElements = searchResultPanel.findElements(By.tagName("a"));
        return !linkElements.isEmpty()
                && linkElements.get(0).getAttribute("href").contains("www.seleniumhq.org");
    }
}
