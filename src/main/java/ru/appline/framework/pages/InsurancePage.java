package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author Arkadiy_Alaverdyan
 * Класс описывающий страничку страхование путешественников
 */
public class InsurancePage extends BasePage {

    @FindBy(xpath = "//h1[@class='uc-form__title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[@class='uc-full__item']")
    private List<WebElement> listInsurance;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return InsurancePage - т.е. остаемся на этой странице
     */
//    @Step("Проверка открытия страницы InsurancePage")
    public InsurancePage checkOpenInsurancePage() {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Страхование", pageTitle.getText());
        return this;
    }


    /**
     * Выбрать тип страхования по имени
     *
     * @param insuranceName - наименование типа страховки
     * @return TariffPage - т.е. переходим на страницу {@link TariffPage}
     */
//    @Step("Выбрать тип страхования '{insuranceName}'")
    public TariffPage selectTypeInsuranceByName(String insuranceName) {
        for (WebElement insuranceItem : listInsurance) {
            WebElement title = insuranceItem.findElement(By.xpath(".//h3[@class='uc-full__header']"));
            if (title.getText().trim().equalsIgnoreCase(insuranceName)) {
                WebElement buttonCheckoutOnline = insuranceItem.findElement(By.xpath(".//b[.='Оформить онлайн']/.."));
                scrollToElementJs(buttonCheckoutOnline);
                elementToBeClickable(buttonCheckoutOnline).click();
                return app.getTariffPage();
            }
        }
        Assert.fail("Тип страховки '" + insuranceName + "' не было найдено на стартовой странице!");
        return app.getTariffPage();
    }


}
