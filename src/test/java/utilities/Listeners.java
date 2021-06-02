package utilities;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class Listeners  extends BrowserUtil implements ITestListener {
	ExtentReports extent = ExtentReportUtil.extentReportGenerator();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentLocal = new ThreadLocal<ExtentTest>();



	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentLocal.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		WebDriver driver = null;
		extentLocal.get().fail(result.getThrowable());
		String testMethodName = result.getMethod().getMethodName();
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			System.out.println("Test Failed");
		}

		try {
			extentLocal.get().addScreenCaptureFromPath(getScreenshot(testMethodName),
					result.getMethod().getMethodName());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

		try {
			MyScreenRecorder.stopRecording();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentLocal.set(test);
		// TODO Auto-generated method stub
		try {
			MyScreenRecorder.startRecording(result.getMethod().getMethodName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}
}
