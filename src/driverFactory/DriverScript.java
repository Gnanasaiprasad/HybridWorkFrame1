package driverFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;
public class DriverScript extends AppUtil {
String inputpath ="C:\\eclipse\\11clockworkframes\\Hybrid_FrameWork\\DataTables\\DataEngine.xlsx";
String outputpath ="C:\\eclipse\\11clockworkframes\\Hybrid_FrameWork\\TestResults\\Hybirdresults.xlsx";
String TCSheet ="MasterTestCases";
String TSSheet ="TestSteps";
ExtentReports report;
ExtentTest test;
@Test
public void startTest() throws Throwable 
{
	//define path for HTML
	report = new ExtentReports("./Reports/Hybrid.HTML");
	boolean res = false;
	String tcres ="";
	//access excel method
	ExcelFileUtil xl =new ExcelFileUtil(inputpath);
	//count no of rows in master test cases sheet
	int TCCount =xl.rowCount(TCSheet);
	//count no of rows in Test step sheet
	int TSCount =xl.rowCount(TSSheet);
Reporter.log("No of rows master TCS sheet::"+TCCount+"     "+"No of rows in Teststep sheet::"+TSCount,true);
	//iterate all rows in master test cases sheet
	for(int i=1;i<=TCCount;i++)
	{
		//read module name cell data
		String ModuleName =xl.getcelldata(TCSheet, i,1);
		//start test case
		test=report.startTest(ModuleName);
		//read execution mode cell
		String executionmode =xl.getcelldata(TCSheet,i,2);
		if(executionmode.equalsIgnoreCase("Y"))
		{
			//read tcid cell from TCSheet
			String tcid =xl.getcelldata(TCSheet,i,0);
			//iterate all rows in TCSheet
			for(int j=1;j<=TSCount;j++)
			{
				//read description cell
				String Description =xl.getcelldata(TCSheet, j, 1);
				//read tsid cell from TSSheet
				String tsid =xl.getcelldata(TSSheet,j,0);
				if(tcid.equalsIgnoreCase(tsid))
				{
					//read keyword cell tssheet
					String keyword =xl.getcelldata(TSSheet,j,3);
					if(keyword.equalsIgnoreCase("AdminLogin"))
					{
						String para1 =xl.getcelldata(TSSheet,j,5);
						String para2 =xl.getcelldata(TSSheet,j,6);
						//call login method
						res =FunctionLibrary.verifyLogin(para1, para2);
						test.log(LogStatus.INFO,Description);
					}
					else if(keyword.equalsIgnoreCase("NewBranch"))
					{
						String para1 =xl.getcelldata(TSSheet,j,5);
						String para2 =xl.getcelldata(TSSheet,j,6);
						String para3 =xl.getcelldata(TSSheet,j,7);
						String para4 =xl.getcelldata(TSSheet,j,8);
						String para5 =xl.getcelldata(TSSheet,j,9);
						String para6 =xl.getcelldata(TSSheet,j,10);
						String para7 =xl.getcelldata(TSSheet,j,11);
						String para8 =xl.getcelldata(TSSheet,j,12);
						String para9 =xl.getcelldata(TSSheet,j,13);
						FunctionLibrary.clickbranches();    
res = FunctionLibrary.verifyBranchCreation(para1,para2,para3,para4,para5,para6,para7,para8,para9);
					}
					else if(keyword.equalsIgnoreCase("BranchUpdation"))
					{
						String para1 =xl.getcelldata(TSSheet,j,5);
						String para2 =xl.getcelldata(TSSheet,j,6);
						String para5 =xl.getcelldata(TSSheet,j,9);
						String para6 =xl.getcelldata(TSSheet,j,10);	
						FunctionLibrary.clickbranches();
						res =FunctionLibrary.verifyBranchUpdation(para1,para2,para5,para6);
					}
					else if(keyword.equals("AdminLogout"))
					{
						res =FunctionLibrary.verifyLogout();
						test.log(LogStatus.PASS,Description);
					}
					String tsres ="";
					if(res)
					{
						//if res is true write pass into status cell
						tsres ="Pass";
						xl.setCellData(TSSheet, j, 4, tsres, outputpath);
					}
					else
					{
						//if res is true write fail into status cell
						tsres ="Fail";
						xl.setCellData(TSSheet, j, 4, tsres, outputpath);	
											}
					tcres=tsres;
				}
				report.endTest(test);
				report.flush();
			}
			//write tcres into TCSheet
			xl.setCellData(TSSheet, i, 3, tcres, outputpath);
			
		}
		else
		{
			//write as blocked which tc is flaged to N in TCSheet
			xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
		}
		
	}
	
	
	
}

}
