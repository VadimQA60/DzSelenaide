package ru.netology;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class cardRequestFordDeliveryTest {

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
        $("[data-test-id=success-notification] .notification__title").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Успешно"));
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Встреча успешно запланирована на " + getFutureDate(4)));


    }


}

