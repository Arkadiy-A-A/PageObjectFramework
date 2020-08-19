package ru.appline.framework.managers;

import ru.appline.framework.pages.RegistrationFormPage;
import ru.appline.framework.pages.StartPage;
import ru.appline.framework.pages.InsurancePage;
import ru.appline.framework.pages.TariffPage;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для управления страничками
 */
public class ManagerPages {

    /**
     * Менеджер страничек
     */
    private static ManagerPages managerPages;

    /**
     * Стартовая страничка
     */
    private StartPage startPage;

    /**
     * Страничка страхование путешественников
     */
    private InsurancePage insurancePage;

    /**
     * Страничка выбора полиса или тарифа
     */
    private TariffPage tariffPage;

    /**
     * Страничка оформления полиса страхования
     */
    private RegistrationFormPage registrationFormPage;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see ManagerPages#getManagerPages()
     */
    private ManagerPages() {
    }

    /**
     * Ленивая инициализация ManagerPages
     *
     * @return ManagerPages
     */
    public static ManagerPages getManagerPages() {
        if (managerPages == null) {
            managerPages = new ManagerPages();
        }
        return managerPages;
    }

    /**
     * Ленивая инициализация {@link ru.appline.framework.pages.StartPage}
     *
     * @return StartPage
     */
    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
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
     * Ленивая инициализация {@link TariffPage}
     *
     * @return TariffPage
     */
    public TariffPage getTariffPage() {
        if (tariffPage == null) {
            tariffPage = new TariffPage();
        }
        return tariffPage;
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
