package compareProject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Numbers {
	WebDriver wd;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browser) throws IOException {

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\SHRUTI\\Downloads\\geckodriver.exe");
			wd = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\SHRUTI\\Downloads\\chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			wd = new ChromeDriver(chromeOptions);
		}
		wd.get("http://localhost:3000/");
	}

	@Parameters("browser")
	@Test(threadPoolSize = 3)
	public void f(String browser) throws IOException, InterruptedException {
		FileInputStream fi = new FileInputStream("C:\\Users\\SHRUTI\\Desktop\\desktop\\Book3.xls");
		HSSFWorkbook wb = new HSSFWorkbook(fi);
		HSSFSheet sh = wb.getSheet("Sheet1");
		int no = sh.getLastRowNum();
		HSSFCell fst, sec, thrd, result;
		for (int i = 1; i <= no; i++) {
			int j = 0;

			fst = sh.getRow(i).getCell(j);
			sec = sh.getRow(i).getCell(j + 1);
			thrd = sh.getRow(i).getCell(j + 2);
			if (browser.equalsIgnoreCase("firefox")) {
				result = sh.getRow(i).createCell(j + 3);
			} else if (browser.equalsIgnoreCase("chrome")) {
				result = sh.getRow(i).createCell(j + 4);
			} else {
				result = sh.getRow(i).createCell(j + 5);
			}

			try {
				wd.findElement(By.name("numFirst")).sendKeys(fst.toString());
				wd.findElement(By.name("numSecond")).sendKeys(sec.toString());
				wd.findElement(By.name("numToCompare")).sendKeys(thrd.toString());
			} catch (Exception e) {

			}
			wd.findElement(By.cssSelector("input[type='submit']")).click();

			Thread.sleep(3000);
			if (wd.getPageSource().contains("First number cannot be empty")
					&& wd.getPageSource().contains("Second number cannot be empty")
					&& wd.getPageSource().contains("Number to compare cannot be empty")) {
				result.setCellValue("First,Second and Compare to number cannot be empty");
			} else if (wd.getPageSource().contains("First number cannot be empty")
					&& wd.getPageSource().contains("Second number cannot be empty")) {
				result.setCellValue("First and Second number cannot be empty");
			} else if (wd.getPageSource().contains("First number cannot be empty")
					&& wd.getPageSource().contains("Number to compare cannot be empty")) {
				result.setCellValue("First and number to compare cannot be empty");
			} else if (wd.getPageSource().contains("Second number cannot be empty")
					&& wd.getPageSource().contains("Number to compare cannot be empty")) {
				result.setCellValue("Second and number to compare cannot be empty");
			} else if (wd.getPageSource().contains("First number cannot be empty")) {
				result.setCellValue("First number cannot be empty");
			} else if (wd.getPageSource().contains("Second number cannot be empty")) {
				result.setCellValue("Second number cannot be empty");
			} else if (wd.getPageSource().contains("Number to compare cannot be empty")) {
				result.setCellValue("Number to compare cannot be empty");
			} else if (wd.getPageSource().contains("First number cannot be a non-number")
					&& wd.getPageSource().contains("Second number cannot be a non-number")
					&& wd.getPageSource().contains("Number to compare cannot be a non-number")) {
				result.setCellValue("First, Second and number to compare cannot be non-number");
			} else if (wd.getPageSource().contains("First number cannot be a non-number")
					&& wd.getPageSource().contains("Second number cannot be a non-number")) {
				result.setCellValue("First and Second number cannot be a non-number");
			} else if (wd.getPageSource().contains("First number cannot be a non-number")
					&& wd.getPageSource().contains("Number to compare cannot be a non-number")) {
				result.setCellValue("First and number to compare cannot be a non-number");
			} else if (wd.getPageSource().contains("Second number cannot be a non-number")
					&& wd.getPageSource().contains("Number to compare cannot be a non-number")) {
				result.setCellValue("Second and number to compare cannot be a non-number");
			} else if (wd.getPageSource().contains("First number cannot be a non-number")) {
				result.setCellValue("First number cannot be a non-number");
			} else if (wd.getPageSource().contains("Second number cannot be a non-number")) {
				result.setCellValue("Second number cannot be a non-number");
			} else if (wd.getPageSource().contains("Number to compare cannot be a non-number")) {
				result.setCellValue("Number to compare cannot be a non-number");
			}

			else {
				try {
					List<WebElement> l = wd.findElements(By.className("modal-body"));
					String s = l.get(0).getText();
					System.out.println(s);
					result.setCellValue(s);
					wd.findElement(By.cssSelector("button[type='button']")).click();
				} catch (Exception e) {

				}
			}
			wd.navigate().refresh();
		}
		fi.close();
		FileOutputStream fo = new FileOutputStream("C:\\Users\\SHRUTI\\Desktop\\desktop\\Book3.xls");
		wb.write(fo);
	}

	@AfterTest
	public void afterTest() {
		wd.quit();
	}

}
