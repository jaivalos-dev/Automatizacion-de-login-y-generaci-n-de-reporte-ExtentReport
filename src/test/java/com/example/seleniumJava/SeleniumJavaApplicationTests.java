package com.example.seleniumJava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeleniumJavaApplicationTests {

	private WebDriver driver;

	@BeforeEach
	void getUp(){
		driver = new ChromeDriver();
	}

	@Test
	void TestWebFaceBook() {
	}

}
