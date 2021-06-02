package PageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.BrowserUtil;
import utilities.DriverUtil;

public class ReditSearchPage {
    public static Logger log=LogManager.getLogger(ReditSearchPage.class.getName());
    public ReditSearchPage(){
        PageFactory.initElements(DriverUtil.getDriver(),this);
    };



    @FindBy(xpath = "//a[text()='Log In']")
    WebElement loginButton;



    public String TopMostTiltle(int TitleNo){
        return DriverUtil.getDriver().findElement(By.xpath("//div[@class='rpBJOHq2PR60pnwJlUyP0']/div["+TitleNo+"]/div[1]/div//h3")).getText();
    }

    public void LoginButton(){
    loginButton.click();
}
    public void Devote(int DevoteRecordNumber){

        DriverUtil.getDriver().findElement(By.xpath("(//div[@class='rpBJOHq2PR60pnwJlUyP0']/div["+DevoteRecordNumber+"]//i[contains(@class,'icon-downvote')])[1]")).click();
    }
}
