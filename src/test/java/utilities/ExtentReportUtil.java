package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtil {
    public static ExtentSparkReporter extentSparkReporter;
    public static ExtentReports extentReports;

    public static ExtentReports extentReportGenerator() {
        String filePath = System.getProperty("user.dir") + "\\test-output\\ExtentReport.html";
        extentSparkReporter = new ExtentSparkReporter(filePath);
        extentSparkReporter.config().setReportName("Web Automation Result"); // name of report
        extentSparkReporter.config().setDocumentTitle("Test Results"); // title of report
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Environment", "QA_1");
        extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("QA Engineer", "Vishal Chopra");
        return extentReports;
    }

}
