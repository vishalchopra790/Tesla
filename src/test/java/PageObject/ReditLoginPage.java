package PageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.DriverUtil;
import utilities.JSUtil;

public class ReditLoginPage {
    public static Logger log=LogManager.getLogger(ReditLoginPage.class.getName());
    public ReditLoginPage(){
        PageFactory.initElements(DriverUtil.getDriver(),this);
    };

    private @FindBy(id = "loginUsername")
    WebElement loginUsername;

    @FindBy(id = "loginPassword")
    WebElement loginPassword;

    @FindBy(xpath = "//button[contains(text(),'Log In')]")
    WebElement loginButton;

    public void login(String username,String password) throws InterruptedException {
        WebElement frame=DriverUtil.getDriver().findElement(By.xpath("//iframe[contains(@class,'_25r3t_')]"));
        DriverUtil.getDriver().switchTo().frame(frame);
        loginUsername.sendKeys(username);
        loginPassword.sendKeys(password);
        loginButton.submit();

        DriverUtil.getDriver().switchTo().defaultContent();
        Thread.sleep(5000);
    }

    public void LoginButton(){
    loginButton.click();
}
}
