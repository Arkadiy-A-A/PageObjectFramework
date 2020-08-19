package ru.appline.framework.utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.appline.framework.managers.DriverManager;

public class MyAllureListener extends AllureJunit4 {

    @Override
    public void testFailure(Failure failure) {
        addScreenshot();
        super.testFailure(failure);
    }


    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] addScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
