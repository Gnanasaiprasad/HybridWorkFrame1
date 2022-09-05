package utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelFileUtil {
XSSFWorkbook wb;
//constructor for reading excel file
public ExcelFileUtil(String excelpath) throws Throwable
{
	FileInputStream fi = new FileInputStream(excelpath); 
			wb = new XSSFWorkbook(fi);
}
//counting no of rows
public int rowCount(String sheetName)
{
	return wb.getSheet(sheetName).getLastRowNum();
}
//count no of cell in row
public int cellCount(String sheetName)
{
	return wb.getSheet(sheetName).getRow(0).getLastCellNum();
}
//read cell data
public String getcelldata(String sheetName,int row,int column)
{
	String data="";
	if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int celldata =(int)wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
				data = String.valueOf(celldata);
	}
	else
	{
 data =wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
}
//method for writing
public void setCellData(String sheetName,int row,int column,String status,String writeexcel) throws Throwable
{
	//get sheet from wb
	XSSFSheet ws =wb.getSheet(sheetName);
	//get row from sheet
	XSSFRow rownum =ws.getRow(row);
	//create cell from row
	XSSFCell cell =rownum.createCell(column);
	//write status
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("Pass"))
	{
		XSSFCellStyle style =wb.createCellStyle();
		XSSFFont font =wb.createFont();
		font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Fail"))
	{
		XSSFCellStyle style =wb.createCellStyle();
		XSSFFont font =wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		ws.getRow(row).getCell(column).setCellStyle(style);	
	}
	else if(status.equalsIgnoreCase("Blocked"));
	{
		XSSFCellStyle style =wb.createCellStyle();
		XSSFFont font =wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		ws.getRow(row).getCell(column).setCellStyle(style);	
	}
	FileOutputStream fo =new FileOutputStream(writeexcel);
	wb.write(fo);
		}
public static void main(String[]args)throws Throwable { 
  ExcelFileUtil xl=new ExcelFileUtil("C:/Book.xlsx");
int rc =xl.rowCount("Login");
int cc =xl.cellCount("Login");
System.out.println(rc+"    "+cc);
for(int i=1;i<=rc;i++)
{
	String user =xl.getcelldata("Login", i, 0);
	String pass =xl.getcelldata("Login", i, 1);
	System.out.println(user+"    "+pass);
	xl.setCellData("Data", i, 2, "Pass","C://Results.xlsx");
	xl.setCellData("Data", i, 2, "Fail","C://Results.xlsx");
	xl.setCellData("Data", i, 2, "Blocked","C://Results.xlsx");
}
}
}
