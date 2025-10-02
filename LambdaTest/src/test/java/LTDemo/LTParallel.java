package LTDemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LTParallel {
	
	
	@Test
	
	@Parameters({"browserName","browserVersion","osVersion"})
	
	
	public void loginTest ( String browserName,String browserVersion,String osVersion) throws MalformedURLException, InterruptedException
	{  
		
		WebDriver driver=null;
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "niteshnkumar");
		ltOptions.put("accessKey", "LT_dRECNnYNiSVT6JwGQiy9POQBmoQlsbYwSnrBty9Ld6ryVx7");
		ltOptions.put("build", "Selenium101_Test_7");
		ltOptions.put("project", "Selenium101_Test_7");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("console", "error");
		ltOptions.put("network", true);
		ltOptions.put("w3c", true);
		ltOptions.put("accessibility", true);
		ltOptions.put("plugin", "java-testNG");
		
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			ChromeOptions browserOptions = new ChromeOptions();
			browserOptions.setPlatformName(osVersion);
			browserOptions.setBrowserVersion(browserVersion);
			browserOptions.setCapability("LT:Options", ltOptions);
		    driver=new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"),browserOptions);
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			EdgeOptions browserOptions = new EdgeOptions();
			browserOptions.setPlatformName(osVersion);
			browserOptions.setBrowserVersion(browserVersion);
			browserOptions.setCapability("LT:Options", ltOptions);
		    driver=new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"),browserOptions);
		}
			
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.lambdatest.com/");
		 String mainWindowHandle=driver.getWindowHandle();
	     WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
			WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[(text()='Explore all Integrations')]")));
			JavascriptExecutor js= (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			
			String platform = System.getProperty("os.name").toLowerCase();
			if (platform.contains("mac")) {
				element.sendKeys(Keys.COMMAND, Keys.RETURN);
				      
			} else {
			    element.sendKeys(Keys.CONTROL, Keys.RETURN);
			}
			
	        
            //element.sendKeys(Keys.CONTROL, Keys.RETURN);
			Set<String> windowHandles = driver.getWindowHandles();
			List<String> windowHandlesList = new ArrayList<>(windowHandles);
			 for (String handle : windowHandlesList) {
		            System.out.println("Window Handle: " + handle);
		        }
			 String secondWindowHandle= windowHandlesList.get(1);
			 driver.switchTo().window(secondWindowHandle);
			 System.out.println(driver.getTitle());
			 Assert.assertEquals(driver.getTitle(), "Plugins and Integrations For Seamless Browser Compatibility Testing | LambdaTest");
			 js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//*[(text()='Codeless Automation')])[2]")));
			 driver.findElement(By.xpath("//a[@href='https://www.lambdatest.com/support/docs/testingwhiz-integration/']")).click();
			 SoftAssert softAssert=new SoftAssert();
			softAssert.assertEquals(driver.getTitle(), "TestingWhiz Itegration With LambdaTest");
			  driver.close();
			driver.switchTo().window(mainWindowHandle);
			Set<String> windowHandles2 = driver.getWindowHandles();
			int windowcount=windowHandles2.size();
			System.out.println(windowcount);
			driver.get("https://www.lambdatest.com/blog/");
			driver.findElement(By.xpath("(//a[@href='https://community.lambdatest.com/'])[2]")).click();
		softAssert.assertEquals(driver.getCurrentUrl(), "https://community.lambdatest.com/");
			driver.quit();
			softAssert.assertAll();
	}

}

	
	
