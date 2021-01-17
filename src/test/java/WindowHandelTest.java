
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandelTest {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() throws Exception {

		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/browser-windows");
		Thread.sleep(2000);

	}

	@Test(priority = 1)
	public void tabTest() throws Exception {

		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.id("tabButton")).click();

		Set<String> allWindows = driver.getWindowHandles();

		for (String childTab : allWindows) {
			if (!parentWindow.equalsIgnoreCase(childTab)) {
				driver.switchTo().window(childTab);
				String tabText = driver.findElement(By.id("sampleHeading")).getText();
				System.out.println("New tab text is: " + tabText);
				Thread.sleep(2000);
				driver.switchTo().window(parentWindow);
				System.out.println("windowHandle");
			}
		}
	}

	@Test(priority = 2)
	public void windowTest() throws Exception {

		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.id("windowButton")).click();

		Set<String> allWindows = driver.getWindowHandles();

		for (String childWindow : allWindows) {
			if (!parentWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
				String windowText = driver.findElement(By.id("sampleHeading")).getText();
				System.out.println("New window text is: " + windowText);
				Thread.sleep(2000);
				driver.switchTo().window(parentWindow);
				System.out.println("childWindow");
			}
		}
	}

	@Test(priority = 3)
	public void msgWindowTest() throws Exception {

		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.id("messageWindowButton")).click();

		Set<String> allWindows = driver.getWindowHandles();

		for (String childMsgWindow : allWindows) {
			if (!parentWindow.equalsIgnoreCase(childMsgWindow)) {
				driver.switchTo().window(childMsgWindow);

				String text = driver.findElement(By.xpath("//body")).getText();
				System.out.println(text);
				Thread.sleep(2000);
				driver.switchTo().window(parentWindow);

			}
		}
	}

	@AfterTest
	public void afterTest() throws Exception {

		Thread.sleep(2000);
		driver.quit();
	}

}
