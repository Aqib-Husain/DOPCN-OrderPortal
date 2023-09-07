package com.dopCN.BaseClass;

import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.dopCN.utilities.ReadConfigProperty;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	ReadConfigProperty Prop = new ReadConfigProperty();
	
	public static WebDriver driver;
	public String url=Prop.getURL();
	public String browser = Prop.getBrowserName();
	public String userName = Prop.getUserName();
	public String password = Prop.getpassword();
	public static Logger logger;
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest eLogger;
	public static Properties prop;
	
	

	public void initilizeBrowzer() {
		logger = LogManager.getLogger(getClass());	
		
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("./logs/ExtentReport.html");
		spark.config().setDocumentTitle("DopCN Automation Report");
		spark.config().setReportName("DOP CN Execution");
		extent.attachReporter(spark);
		eLogger = extent.createTest("Browser Setup");
		
		
		browser = browser.toLowerCase();
		
		logger.trace("This is trace");
		logger.debug("This is debug");
		logger.info("chrome browser is opening");
		logger.warn("This is warning");
		logger.fatal("This is Fatal");
		logger.error("This is error");
		
		switch (browser) {
		case "chrome":	
			eLogger.info("Opening the Chrome browser");
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(option);
			initialize(url);
			break;
		case "firefox":
			eLogger.info("Opening the Fire Fox browser");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			initialize(url);
			break;
		case "edge":
			eLogger.info("Opening the Edge browser");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			initialize(url);

		default:
			System.out.println("Please enter a browser chrome, firefox or edge");
		}

	}

	public void initialize(String url) {
		eLogger.info("Maximizing browser");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		eLogger.info("Opening the URL");
		driver.get(url);
	}
	
	public void closeSession() {
		driver.quit();
	}
}
