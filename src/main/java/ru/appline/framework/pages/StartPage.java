package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * @author Arkadiy_Alaverdyan
 * Стартовая страница приложения
 */
public class StartPage extends BasePage {

    @FindBy(xpath = "//button[@class='lg-menu__link']")
    private List<WebElement> menuBaseList;

    @FindBy(xpath = "//a[@class='lg-menu__sub-link' and text()]")
    private List<WebElement> menuSubList;

    /**
     * Функция наведения мыши на любой пункт меню
     *
     * @param nameBaseMenu - наименование меню
     * @return StartPage - т.е. остаемся на этой странице
     */
    public StartPage selectBaseMenu(String nameBaseMenu) {
        for (WebElement menuItem : menuBaseList) {
            if (menuItem.getText().equalsIgnoreCase(nameBaseMenu)) {
                action.moveToElement(menuItem).build().perform();
                return this;
            }
        }
        Assert.fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
        return this;
    }

    /**
     * Функция клика на любое подменю
     *
     * @param nameSubMenu - наименование подменю
     * @return InsurancePage - т.е. переходим на страницу {@link InsurancePage}
     */
    public InsurancePage selectSubMenu(String nameSubMenu) {
        for (WebElement menuItem : menuSubList) {
            if (menuItem.getText().equalsIgnoreCase(nameSubMenu)) {
                action.moveToElement(menuItem).build().perform();
                wait.until(ExpectedConditions.visibilityOf(menuItem)).click();
                return app.getInsurancePage();
            }
        }
        Assert.fail("Подменю '" + nameSubMenu + "' не было найдено на стартовой странице!");
        return app.getInsurancePage();
    }


}
