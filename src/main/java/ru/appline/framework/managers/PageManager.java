package ru.appline.framework.managers;

import ru.appline.framework.pages.RegistrationFormPage;
import ru.appline.framework.pages.HomePage;
import ru.appline.framework.pages.InsurancePage;
import ru.appline.framework.pages.SelectInsuranceServicePage;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для управления страничками
 */
public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страничка
     */
    private HomePage homePage;

    /**
     * Страничка страхование путешественников
     */
    private InsurancePage insurancePage;

    /**
     * Страничка выбора полиса или тарифа
     */
    private SelectInsuranceServicePage selectInsuranceServicePage;

    /**
     * Страничка оформления полиса страхования
     */
    private RegistrationFormPage registrationFormPage;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link HomePage}
     *
     * @return StartPage
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    /**
     * Ленивая инициализация {@link InsurancePage}
     *
     * @return InsurancePage
     */
    public InsurancePage getInsurancePage() {
        if (insurancePage == null) {
            insurancePage = new InsurancePage();
        }
        return insurancePage;
    }

    /**
     * Ленивая инициализация {@link SelectInsuranceServicePage}
     *
     * @return TariffPage
     */
    public SelectInsuranceServicePage getSelectInsuranceServicePage() {
        if (selectInsuranceServicePage == null) {
            selectInsuranceServicePage = new SelectInsuranceServicePage();
        }
        return selectInsuranceServicePage;
    }

    /**
     * Ленивая инициализация {@link ru.appline.framework.pages.RegistrationFormPage}
     *
     * @return RegistrationFormPage
     */
    public RegistrationFormPage getRegistrationFormPage() {
        if (registrationFormPage == null) {
            registrationFormPage = new RegistrationFormPage();
        }
        return registrationFormPage;
    }
}
