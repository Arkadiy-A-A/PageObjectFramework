package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Arkadiy_Alaverdyan
 * Класс описывающий страничку страхование путешественников
 */
public class InsurancePage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'kit-col_xs_12')]/h1")
    private WebElement pageTitle;

    @FindBy(xpath = "//b[text()='Оформить онлайн']")
    private WebElement checkoutOnlineButton;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return InsurancePage - т.е. остаемся на этой странице
     */
    @Step("Проверка открытия страницы InsurancePage")
    public InsurancePage checkOpenInsurancePage() {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Страхование путешественников", pageTitle.getText());
        return this;
    }


    /**
     * Клик по кнопке "Оформить онлайн"
     *
     * @return TariffPage - т.е. переходим на страницу {@link TariffPage}
     */
    @Step("Клик по кнопке 'Оформить онлайн'")
    public TariffPage clickBtnCheckoutOnline() {
        scrollToElementJs(checkoutOnlineButton);
        elementToBeClickable(checkoutOnlineButton).click();
        return app.getTariffPage();
    }


}
