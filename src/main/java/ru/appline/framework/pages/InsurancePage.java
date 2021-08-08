package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Arkadiy_Alaverdyan
 * Класс описывающий страничку страхование путешественников
 */
public class InsurancePage extends BasePage {

    @FindBy(xpath = "//h1[@data-test-id]")
    private WebElement title;


    @FindBy(xpath = "//*[text()='Оформить онлайн']/../../a[@data-test-id]")
    private WebElement buttonCheckoutOnline;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return InsurancePage - т.е. остаемся на этой странице
     */
    public InsurancePage checkOpenInsurancePage() {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Страхование путешественников", title.getText());
        return this;
    }


    public SelectInsuranceServicePage checkoutOnline() {
        waitUtilElementToBeClickable(buttonCheckoutOnline).click();
        return pageManager.getSelectInsuranceServicePage().checkOpenSelectInsuranceServicePage();
    }


}
