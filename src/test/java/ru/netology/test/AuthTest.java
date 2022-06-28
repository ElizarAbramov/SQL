package ru.netology.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class AuthTest {
    @BeforeEach
    void setUpPage() {
        open("http://localhost:9999/");
    }

    @Test
    void myTest() {
        var authInfo = DataHelper.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode();
        var dashBoardPage = verificationPage.validVerify(verificationCode);
        dashBoardPage.visible();
    }

    @AfterEach
    void shouldClearData() {
        DataHelper.clearData();
    }
}