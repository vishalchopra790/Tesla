package utilities;

import PageObject.*;


public class Pages {

    public ReditHomePage reditHomePage;
    public ReditSearchPage reditSearchPage;
    public ReditLoginPage reditLoginPage;
    public Pages() {

        reditHomePage =new ReditHomePage();
        reditSearchPage = new ReditSearchPage();
        reditLoginPage=new ReditLoginPage();

    }
}
