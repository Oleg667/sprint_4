package Praktikum;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.OrderPage;
import pages.RentPage;
import pages.PopUpPage;
import static org.junit.Assert.assertTrue;
import pages.DriverRule;
import org.junit.Rule;



// Указываем, что данный класс будет использовать параметризованные тесты
    @RunWith(Parameterized.class)
    public class TestOrder {
    private MainPage mainPage;
    private final String name; // Имя арендатора
    private final String surname; // Фамилия арендатора
    private final String address; // Адрес арендатора
    private final int stateMetroNumber; // Номер станции метро
    private final String telephoneNumber; // Телефон арендатора
    private final String date; // Дата аренды
    private final String duration; // Длительность аренды
    private final String colour; // Цвет самоката
    private final String comment; // Комментарий от пользователя
    private final String expectedHeader = "Заказ оформлен"; // Ожидаемый заголовок после оформления заказа

    @Rule // Используем JUnit Rule для управления WebDriver
    public DriverRule driverRule = new DriverRule();

    // Конструктор для параметров теста
    public TestOrder(String name, String surname, String address, int stateMetroNumber, String telephoneNumber,
                     String date, String duration, String colour, String comment) {
        this.name = name; // Устанавливаем имя
        this.surname = surname; // Устанавливаем фамилию
        this.address = address; // Устанавливаем адрес
        this.stateMetroNumber = stateMetroNumber; // Устанавливаем номер метро
        this.telephoneNumber = telephoneNumber; // Устанавливаем номер телефона
        this.date = date; // Устанавливаем дату
        this.duration = duration; // Устанавливаем длительность аренды
        this.colour = colour; // Устанавливаем цвет самоката
        this.comment = comment; // Устанавливаем комментарий
    }

    @Parameterized.Parameters(name = "Аренда на: {6}")
    public static Object[][] getParameters() {
        return new Object[][]{
                // Массив параметров для каждого тестового случая
                {"Аполинарий", "Иванов", "ул Первая 1", 123, "79999999999", "24.06.2024", "сутки", "GREY", "Позвоните"},
                {"Сергей", "Петров", "ул Вторая 2", 7, "79000000000", "25.06.2024", "двое суток", "BLACK", "Стучите"},
                {"Анна", "Рябова", "ул Третья 3", 10, "79555555555", "26.06.2024", "трое суток", "BLACK", "Кричите"},
                {"Иван", "Иванов", "ул Четвертая 1", 123, "79999999999", "27.06.2024", "четверо суток", "GREY", "Подарочная упаковка"},
                {"Петр", "Петров", "ул Пятая 2", 7, "79000000000", "29.06.2024", "пятеро суток", "BLACK", "Заранее позвоните"},
                {"Анна", "Рябова", "ул Шестая 3", 10, "79555555555", "30.06.2024", "шестеро суток", "BLACK", "Заранее позвоните"},
                {"Анна", "Рябова", "ул Седьмая 3", 10, "79555555555", "30.06.2024", "семеро суток", "BLACK", "Заранее позвоните"},
        };
    }

    @Before
    public void setUp() {
        // Создаем экземпляр WebDriver через DriverRule
        WebDriver driver = driverRule.getDriver();
        mainPage = new MainPage(driver);
        mainPage.openPage(); // Открываем главную страницу
    }

    @Test
    public void TestScooterOrderOrder() {
        assertTrue("Кнопка Заказать в хедере не активна или не найдена", mainPage.isOrderButtonHeaderEnabled());
        mainPage.clickOrderButtonHeader();
        new OrderPage(driverRule.getDriver())
                .waitForLoadOrderPage()
                .inputName(name)
                .inputSurname(surname)
                .inputAddress(address)
                .changeStateMetro(stateMetroNumber)
                .inputTelephone(telephoneNumber)
                .clickNextButton();

        new RentPage(driverRule.getDriver())
                .waitAboutRentHeader()
                .inputDate(date)
                .inputDuration(duration)
                .changeColour(colour)
                .inputComment(comment)
                .clickButtonCreateOrder();

        PopUpPage popUpPage = new PopUpPage(driverRule.getDriver());
        popUpPage.clickButtonYes();
        assertTrue(popUpPage.getHeaderAfterCreateOrder().contains(expectedHeader));
    }


}