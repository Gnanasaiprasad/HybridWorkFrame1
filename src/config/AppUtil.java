package config;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
public class AppUtil {
	public static WebDriver driver;
	public static Properties config;
	@BeforeSuite
	public static void setup()throws Throwable
	{
		config = new Properties();
config.load(new FileInputStream("C:\\eclipse\\11clockworkframes\\Hybrid_FrameWork\\PropertyFiles\\Environment.properties"));
if(config.getProperty("Browser").equalsIgnoreCase("chrome"))
{
	System.setProperty("weddriver.chrome.driver", "C://Chromedriver.exe");
		System.out.println("Executing on chrome browser");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(config.getProperty("url"));
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}
else if(config.getProperty("Browser").equalsIgnoreCase("firefox"))
{
	System.out.println("Executing on chrome browser");
	driver = new FirefoxDriver();
		driver.get(config.getProperty("url"));
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}
else
{
	Reporter.log("Browser valu is not matching",true);
}
	}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
}
}	

