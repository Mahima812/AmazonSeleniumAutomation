package automation.TestComponents;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import automation.AbstractComponents.AbstractComponents;
import io.qameta.allure.Allure;

public class Listeners implements ITestListener{

	String test;
	
	@Override
	public void onTestStart(ITestResult result) {
		test=result.getMethod().getMethodName();
		Allure.step("Starting test: "+ test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test=result.getMethod().getMethodName();
		Allure.step("Test Passed: "+ test);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test=result.getMethod().getMethodName();
		Allure.step("Test Failed: "+ test);
		try {
			AbstractComponents.getScreenshot(test);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test=result.getMethod().getMethodName();
		Allure.step("Test Skipped: "+ test);
	}

}
