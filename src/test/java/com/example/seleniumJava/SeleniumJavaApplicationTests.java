package com.example.seleniumJava;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest
class SeleniumJavaApplicationTests {

	private ExtentReports extentReport;
	private WebDriver driver;

	@BeforeEach
	void getUp(){
		driver = new ChromeDriver();
		extentReport = new ExtentReports();
		driver.get("https://demoqa.com/login");
		driver.manage().window().maximize();
	}

	@Test
	void TestWebLogin() {

		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("Report/spark_report.html");
		extentReport.attachReporter(extentSparkReporter);

		ExtentTest testlog = extentReport.createTest("Test web DemoQA");

		// Si se desea buscar algo por xpath es posible hacerlo de la siguiente manera:
		// " *//input[@type='submit' and @value='Enviar']* "
		// " *//button[contains(@class, 'btn') and text()='login']* "

		//Credenciales de pruebas: Jaiva, Javi0712**

		driver.findElement(By.id("userName")).sendKeys("Jaiva");
		driver.findElement(By.id("password")).sendKeys("Javi0712**");

		// Desplazar al botón y hacer click en 'Login'
		WebElement btn = driver.findElement(By.id("login"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
		btn.click();

		try {

			//Se crea una espera explicita para que el elemento termine de cargar
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
			String text = element.getText();

			//Verificamos el contenido de la etiqueta p
			if (text.equals("Invalid username or password!")) {
				System.out.println("Login Failed");
				testlog.log(Status.FAIL, "Failed at login");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Login Succesfully");
			testlog.log(Status.PASS, "Login Succesfully");
		} catch (TimeoutException e){
			System.out.println("Login Succesfully");
			testlog.log(Status.PASS, "Login Succesfully");
		}

		driver.close();
		extentReport.flush();
	}

}
