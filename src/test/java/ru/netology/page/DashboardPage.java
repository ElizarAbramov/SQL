package ru.netology.page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    public void visible() {
        $("[data-test-id =dashboard]").shouldBe(visible);
        new DashboardPage();
    }
}