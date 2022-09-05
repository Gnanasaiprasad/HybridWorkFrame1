package commonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import config.AppUtil;
public class FunctionLibrary extends AppUtil{
//method for login
public static boolean verifyLogin(String username,String password)
{
driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(username);
driver.findElement(By.xpath(config.getProperty("ObjPass"))).sendKeys(password);
driver.findElement(By.xpath(config.getProperty("ObjLogin"))).click();
String expected ="Adminflow";
String actual =driver.getCurrentUrl();
if(actual.toLowerCase().contains(expected.toLowerCase()))
{
	Reporter.log("Login sucess::"+expected+"     "+actual,true);
	return true;
}
else
{
	Reporter.log("Login fail::"+expected+"     "+actual,true);
	return false;
}
}
//method click branches
public static void clickbranches() throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("ObjBranches"))).click();
	Thread.sleep(3000);
}
//method for branch creation
public static Boolean verifyBranchCreation(String branchname,String Address1,
String Address2,String Address3,String Area,String Zipcode,String country,String state,String city) throws Throwable
{
driver.findElement(By.xpath(config.getProperty("ObjNewBranch"))).click();
Thread.sleep(3000);
driver.findElement(By.xpath(config.getProperty("ObjBranchName"))).sendKeys(branchname);
driver.findElement(By.xpath(config.getProperty("ObjAddress1"))).sendKeys(Address1);
driver.findElement(By.xpath(config.getProperty("ObjAddress2"))).sendKeys(Address2);
driver.findElement(By.xpath(config.getProperty("ObjAddress3"))).sendKeys(Address3);
driver.findElement(By.xpath(config.getProperty("ObjArea"))).sendKeys(Area);
driver.findElement(By.xpath(config.getProperty("ObjZipcode"))).sendKeys(Zipcode);
new Select(driver.findElement(By.xpath(config.getProperty("ObjCountry")))).selectByVisibleText(country);
Thread.sleep(2000);
new Select(driver.findElement(By.xpath(config.getProperty("ObjState")))).selectByVisibleText(state);
Thread.sleep(2000);
new Select(driver.findElement(By.xpath(config.getProperty("Objcity")))).selectByVisibleText(city);
driver.findElement(By.xpath(config.getProperty("Objsubmit"))).click();
//capture alert text
String expectedbranchalert =driver.switchTo().alert().getText();
Thread.sleep(5000);
driver.switchTo().alert().accept();
String actualbranchalert ="New Branch with";
if(expectedbranchalert.toLowerCase().contains(actualbranchalert.toLowerCase()))
{
	Reporter.log(expectedbranchalert,true);
	return true;
}
else
{
	Reporter.log("Branch Creation Fail",true);
	return false;
}
}
//method for branch updation
public static boolean verifyBranchUpdation(String branch,String Address,String area,String zipcode) throws Throwable
{
driver.findElement(By.xpath(config.getProperty("ObjEdit"))).click();
Thread.sleep(3000);
WebElement element =driver.findElement(By.xpath(config.getProperty("ObjBranch")));
element.clear();
element.sendKeys(branch);
WebElement element1 =driver.findElement(By.xpath(config.getProperty("ObjAddress")));
element1.clear();
element1.sendKeys(Address); 
WebElement element2 =driver.findElement(By.xpath(config.getProperty("ObjAreaName")));
element2.clear();
element2.sendKeys(area);
WebElement element3 =driver.findElement(By.xpath(config.getProperty("Objzip")));
element3.clear();
element3.sendKeys(zipcode);
driver.findElement(By.xpath(config.getProperty("ObjUpdate")));
Thread.sleep(4000);
String expectedbranchupdatealert =driver.switchTo().alert().getText();
Thread.sleep(4000);
driver.switchTo().alert().accept();
String actualupdatealert ="Branch Updated";
if(expectedbranchupdatealert.toLowerCase().contains(actualupdatealert.toLowerCase()))
{
	Reporter.log(expectedbranchupdatealert,true);
	return true;
}
else
{
Reporter.log("branch update Fail",true);
 return false;
}
}
//logout method
public static boolean verifyLogout() throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("ObjLogout")));
	Thread.sleep(4000);
	if(driver.findElement(By.xpath(config.getProperty("ObjLogin"))).isDisplayed())
	{
		Reporter.log("Login Success",true);
		return true;
	}
	else
	{
		Reporter.log("Login Fail",true);
		return false;
		
	}
	
}

}

	

