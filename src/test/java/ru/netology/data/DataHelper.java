package ru.netology.data;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @SneakyThrows
    public static VerificationCode getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC;";
        var runner = new QueryRunner();
        String code;
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app_db", "elyaz", "322")
        ) {
            code = runner.query(conn, codeSQL, new ScalarHandler<>());

            return new VerificationCode(code);
        }


    }

    @SneakyThrows
    public static void clearData() {
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app_db", "elyaz", "322")
        ) {
            runner.execute(conn, "DELETE FROM auth_codes;");
            runner.execute(conn, "DELETE FROM card_transactions;");
            runner.execute(conn, "DELETE FROM cards;");
            runner.execute(conn, "DELETE FROM users;");
        }
    }
}
