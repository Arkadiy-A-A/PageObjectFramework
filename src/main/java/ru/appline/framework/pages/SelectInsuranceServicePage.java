package ru.appline.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Arkadiy_Alaverdyan
 * Класс описывающий страничку выбора полиса
 */
public class SelectInsuranceServicePage extends BasePage {

    @FindBy(xpath = "//h3[text()='Минимальная']")
    private WebElement insuranceCoverageAmount;

    @FindBy(xpath = "//button[text()='Оформить']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//div[@_ngcontent-c4]/a[.='Выбор полиса']/..")
    WebElement title;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return SelectInsuranceServicePage - т.е. остаемся на этой странице
     */
    public SelectInsuranceServicePage checkOpenSelectInsuranceServicePage() {
        waitUtilElementToBeVisible(title);
        wait.until(ExpectedConditions.attributeContains(title, "class", "col-4 step-element active"));
        return this;
    }

    /**
     * Выбор тарифа страхования
     *
     * @return SelectInsuranceServicePage - т.е. остаемся на этой странице
     */
    public SelectInsuranceServicePage selectTariffMin() {
        scrollToElementJs(insuranceCoverageAmount);
        waitUtilElementToBeClickable(insuranceCoverageAmount).click();
        return this;
    }

    /**
     * Клик по кнопке "Оформить"
     *
     * @return RegistrationFormPage - т.е. переходим на страницу {@link RegistrationFormPage}
     */
    public RegistrationFormPage checkoutInsuranceOnline() {
        scrollToElementJs(checkoutButton);
        waitUtilElementToBeClickable(checkoutButton).click();
        return pageManager.getRegistrationFormPage().checkOpenRegistrationFormPage();
    }


}
