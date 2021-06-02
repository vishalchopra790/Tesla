package PageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReditHomePage {
    public static Logger log=LogManager.getLogger(ReditHomePage.class.getName());
    public ReditHomePage(){
        PageFactory.initElements(DriverUtil.getDriver(),this);
    };

    @FindBy(id = "header-search-bar")
    WebElement search;

    @FindBy(xpath = "//div[contains(@class,'menulist')]//a[1]")
    WebElement rechargeSection;

    public void SearchFromTopSearchbar(String searchsearch){
        search.click();
        search.sendKeys(searchsearch);
        search.sendKeys(Keys.ENTER);
    }

}
