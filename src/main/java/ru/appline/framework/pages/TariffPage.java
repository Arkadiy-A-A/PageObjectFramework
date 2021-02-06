package ru.appline.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Arkadiy_Alaverdyan
 * Класс описывающий страничку выбора полиса
 */
public class TariffPage extends BasePage {

    @FindBy(xpath = "//h3[text()='Минимальная']")
    private WebElement insuranceCoverageAmount;

    @FindBy(xpath = "//button[text()='Оформить']")
    private WebElement checkoutButton;


    /**
     * Выбор тарифа страхования
     *
     * @return TariffPage - т.е. остаемся на этой странице
     */
//    @Step("Выбираем тариф страхования 'Минимальный'")
    public TariffPage selectTariffMin() {
        scrollToElementJs(insuranceCoverageAmount);
        insuranceCoverageAmount.click();
        return this;
    }

    /**
     * Клик по кнопке "Оформить"
     *
     * @return RegistrationFormPage - т.е. переходим на страницу {@link RegistrationFormPage}
     */
//    @Step("Клик по кнопке 'Оформить'")
    public RegistrationFormPage clickBtnArrange() {
        scrollToElementJs(checkoutButton);
        elementToBeClickable(checkoutButton).click();
        return app.getRegistrationFormPage();
    }


}
