package learningTestNG;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LibraryClass {
	XSSFWorkbook excel;

	public void externalTimeMethod(WebDriver driver, int time, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}

	public void ExcelPath(String path) {
		try {
			File file = new File(path);
			FileInputStream inputStream = new FileInputStream(file);
			excel = new XSSFWorkbook(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getValueFromExcel(int sheetNumber, int row, int cell) {
		XSSFSheet sheet = excel.getSheetAt(sheetNumber);
		String value = sheet.getRow(row).getCell(cell).getStringCellValue();
		return value;
	}
}
