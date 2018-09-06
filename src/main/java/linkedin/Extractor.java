package linkedin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Extractor {
    public static void main(String[] args) throws IOException, InterruptedException {
        Extractor extractor = new Extractor();
        extractor.run();
    }

    public void run() throws InterruptedException, IOException {
        WebDriver driver = new ChromeDriver();
        String XPATH = "//*[contains(@data-control-name, 'A_jobssearch_job_result_click')]";
        driver.get("https://www.linkedin.com/jobs/search/?keywords=Data%20Engineer&location=United%20Kingdom&locationId=gb%3A0");
        Thread.sleep(20000);
        System.out.println("ready....");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        waiting(jse);
        List<WebElement> elements = driver.findElements(By.xpath(XPATH));
        addToFile(elements);
        String BASE_URL = "https://www.linkedin.com/jobs/search/?keywords=Data%20Engineer&location=United%20Kingdom&locationId=gb%3A0&start=";
        System.out.println("Base URL: Done");
        for (int i = 25; i <= 125; i += 25) {
            String RESULT = BASE_URL + String.valueOf(i);
            driver.get(RESULT);
            waiting(jse);
            System.out.println(i);
            elements = driver.findElements(By.xpath(XPATH));
            addToFile(elements);
        }

        System.out.println("done");

    }

    public void waiting(JavascriptExecutor jse) throws InterruptedException {
        for (int i = 50; i <= 700; i += 50) {
            jse.executeScript("window.scrollBy(0," + String.valueOf(i) + ")", "");
            Thread.sleep(2000);
            System.out.println(i);
        }

    }

    public void addToFile(List<WebElement> allElements) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt", true));
        for (WebElement element : allElements) {
            writer.write(element.getAttribute("href"));
            writer.newLine();
            writer.flush();
        }
        writer.close();
    }
}
