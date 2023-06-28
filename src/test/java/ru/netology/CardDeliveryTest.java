package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    private String getFutureDate(int addDays) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(addDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = futureDate.format(formatter);
        return formattedDate;


    }

    @Test

    public void CardDeliveryTest() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $(".calendar-input__custom-control input").doubleClick().sendKeys(getFutureDate(4));
        $("[data-test-id=name] input").setValue("Вадим Пенкин");
        $("[data-test-id=phone] input").setValue("+79168268994");
        $(".checkbox__box").click();
        $(".button").click();
        WebElement title = $("[data-test-id=success-notification] .notification__title").shouldBe(visible, Duration.ofSeconds(15));
        Assertions.assertEquals("Успешно!", title.getText());
        WebElement content = $("[data-test-id=success-notification] .notification__content");
        Assertions.assertEquals("Встреча успешно запланирована на " + getFutureDate(4), content.getText());

    }


}

