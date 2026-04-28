package miniProject.base;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest implements ITestListener, ISuiteListener {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();
    private static ExtentReports extent;

    // ===== Driver getters/setters =====
    public static WebDriver getDriver() { return tlDriver.get(); }
    public static void setDriver(WebDriver driver) { tlDriver.set(driver); }

    public static ExtentTest getTest() { return tlTest.get(); }
    public static void setTest(ExtentTest test) { tlTest.set(test); }

    // ===== Suite Lifecycle (Extent Report) =====
    @Override
    public void onStart(ISuite suite) {
        new File("target/extent").mkdirs();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/extent/index.html");
        spark.config().setReportName("Rediff Registration - Automation Report");
        spark.config().setDocumentTitle("Automation Results");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Project", "MiniProject - Rediff");
        extent.setSystemInfo("Tester", "Ankush Tiwari");
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) {
            extent.flush();
        }
    }

    // ===== Test Lifecycle (per test method) =====
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName())
                .assignCategory(result.getTestClass().getName());
        setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getTest().pass("Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        getTest().fail(result.getThrowable());
        String path = takeScreenshot(result.getMethod().getMethodName());
        if (path != null) {
            getTest().addScreenCaptureFromPath(path);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getTest().skip(result.getThrowable() == null ? "Skipped" : result.getThrowable().getMessage());
    }

    // ===== Screenshot helper =====
    private String takeScreenshot(String methodName) {
        try {
            WebDriver driver = getDriver();
            if (driver == null) return null;
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String destPath = "target/extent/screenshots/" + methodName + "_" + timestamp + ".png";
            File dest = new File(destPath);
            dest.getParentFile().mkdirs();
            Files.copy(src.toPath(), dest.toPath());
            return destPath;
        } catch (IOException e) {
            return null;
        }
    }

    // ===== Framework-level setup/teardown to be used by test classes =====
    public void startDriver(String browser, boolean headless, String url) {
        WebDriver driver = DriverFactory.createInstance(browser, headless);
        DriverFactory.commonSetup(driver);
        setDriver(driver);
        if (url != null && !url.isEmpty()) {
            driver.get(url);
        }
    }

    public void stopDriver() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            tlDriver.remove();
        }
    }
}