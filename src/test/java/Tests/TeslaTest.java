package Tests;

import org.testng.annotations.Test;
import utilities.Base;
import utilities.BrowserUtil;
import utilities.ConfigReader;
import utilities.DriverUtil;

public class TeslaTest extends Base {
    @Test
    public void E2ETest() throws InterruptedException {
        DriverUtil.getDriver().get(ConfigReader.getProperty("teslaurl"));
        pages.reditHomePage.SearchFromTopSearchbar("gaming");
        System.out.println(pages.reditSearchPage.TopMostTiltle(1));
        pages.reditSearchPage.LoginButton();
        pages.reditLoginPage.login("vishalchopra19997", "Vishal@18");
        pages.reditSearchPage.Devote(2);
        BrowserUtil.getScreenshot("xxxx");
    }

}
