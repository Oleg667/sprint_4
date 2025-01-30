package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;

public class MainPage {
    private WebDriver driver;
    private JavascriptExecutor jsExecutor;

    // Конструктор
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    // Метод для открытия главной страницы
    public void openPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    // Метод для нажатия на вопрос в FAQ
    public void clickQuestion(String question) {
        WebElement questionButton = driver.findElement(By.xpath("//div[contains(text(), '" + question + "')]"));
        scrollToElement(questionButton);
        jsExecutor.executeScript("arguments[0].click();", questionButton);
    }

    // Метод для получения ответа на вопрос
    public String getAnswerTextById(String locatorAccordion) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorAccordion)));
        return answerElement.getText();
    }

    // Метод для прокрутки до элемента аккордеона
    private void scrollToElement(WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Локатор кнопки "Заказать" в хедере
    private final By orderButtonHeader = By.className("Button_Button__ra12g");

    // Локатор второй кнопки "Заказать"

    private final By orderButtonUltraBig = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Метод для проверки активности кнопки "Заказать" в хедере
    public boolean isOrderButtonHeaderEnabled() {
        return driver.findElement(orderButtonHeader).isEnabled();
    }

    // Метод для проверки активности второй кнопки "Заказать" с перехватом исключения если объект не найден
    public boolean isOrderButtonUltraBigEnabled() {
        try {
            WebElement button = driver.findElement(orderButtonUltraBig); // Попытка найти элемент
            scrollToElement(button); // Прокрутить к элементу
            return button.isEnabled(); // Проверить, активна ли кнопка
        } catch (NoSuchElementException e) {
            System.err.println("Элемент не найден: " + orderButtonUltraBig);
            return false; // Возвращаем false, если элемент не найден
        }
    }

    // Метод нажатия на кнопку "Заказать" в хедере
    public void clickOrderButtonHeader() {
        WebElement button = driver.findElement(orderButtonHeader);
        button.click();
    }

    // Метод нажатия на вторую кнопку "Заказать"
    public void clickOrderButtonUltraBig() {
        WebElement button = driver.findElement(orderButtonUltraBig); // Найти элемент
        scrollToElement(button); // Прокрутить к элементу
        button.click(); // Нажать на кнопку
    }


}