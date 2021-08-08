package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Arkadiy_Alaverdyan
 * Класс описывающий страничку оформления полиса страхования
 */
public class RegistrationFormPage extends BasePage {

    @FindBy(xpath = "//div[@_ngcontent-c4]/a[.='Оформление']/..")
    private WebElement title;

    @FindBy(id = "surname_vzr_ins_0")
    private WebElement insSurName;

    @FindBy(id = "name_vzr_ins_0")
    private WebElement insName;

    @FindBy(id = "birthDate_vzr_ins_0")
    private WebElement insDateOfBirth;

    @FindBy(id = "person_lastName")
    private WebElement personLastName;

    @FindBy(id = "person_firstName")
    private WebElement personFirstName;

    @FindBy(id = "person_middleName")
    private WebElement personMiddleName;

    @FindBy(id = "person_birthDate")
    private WebElement personBirthDate;

    @FindBy(id = "passportSeries")
    private WebElement passportSeries;

    @FindBy(id = "passportNumber")
    private WebElement passportNumber;

    @FindBy(id = "documentDate")
    private WebElement passportDocumentDate;

    @FindBy(id = "documentIssue")
    private WebElement passportDocumentIssue;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "repeatEmail")
    private WebElement repeatEmail;

    @FindBy(xpath = "//button[contains(.,'Продолжить')]")
    private WebElement btnContinue;

    @FindBy(xpath = "//div[@class='alert-form alert-form-error']")
    private WebElement errorMessageAlert;

    /**
     * роверка открытия страницы, путём проверки title страницы
     *
     * @return RegistrationFormPage - т.е. остаемся на этой странице
     */
    public RegistrationFormPage checkOpenRegistrationFormPage() {
        waitUtilElementToBeVisible(title);
        wait.until(ExpectedConditions.attributeContains(title, "class", "col-4 step-element active"));
        return this;
    }

    /**
     * Метод заполнения полей
     *
     * @param nameField - имя веб элемента, поля ввода
     * @param value     - значение вводимое в поле
     * @return RegistrationFormPage - т.е. остаемся на этой странице
     */
    public RegistrationFormPage fillField(String nameField, String value) {
        WebElement element = null;
        switch (nameField) {
            case "Застрахованные - Фамилия":
                fillInputField(insSurName, value);
                element = insSurName;
                break;
            case "Застрахованные - Имя":
                fillInputField(insName, value);
                element = insName;
                break;
            case "Застрахованные - Дата рождения":
                fillDateField(insDateOfBirth, value);
                element = insDateOfBirth;
                break;
            case "Страхователь - Фамилия":
                fillInputField(personLastName, value);
                element = personLastName;
                break;
            case "Страхователь - Имя":
                fillInputField(personFirstName, value);
                element = personFirstName;
                break;
            case "Страхователь - Отчество":
                fillInputField(personMiddleName, value);
                element = personMiddleName;
                break;
            case "Страхователь - Дата рождения":
                fillDateField(personBirthDate, value);
                element = personBirthDate;
                break;
            case "Страхователь - Серия паспорта":
                fillInputField(passportSeries, value);
                element = passportSeries;
                break;
            case "Страхователь - Номер паспорта":
                fillInputField(passportNumber, value);
                element = passportNumber;
                break;
            case "Страхователь - Дата выдачи":
                fillDateField(passportDocumentDate, value);
                element = passportDocumentDate;
                break;
            case "Страхователь - Кем выдан":
                fillInputField(passportDocumentIssue, value);
                element = passportDocumentIssue;
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Оформления страхования путешественников'");

        }
        Assert.assertEquals("Поле '" + nameField + "' было заполнено некорректно",
                value, element.getAttribute("value"));
        return this;
    }

    /**
     * Проверка ошибки относящаяся к конкретному полю на форме
     *
     * @param nameField  - имя веб элемента
     * @param errMassage - ошибка проверяемая которая отображается возле этого поля
     * @return RegistrationFormPage - т.е. остаемся на этой странице
     */
    public RegistrationFormPage checkErrorMessageAtField(String nameField, String errMassage) {
        WebElement element = null;
        switch (nameField) {
            case "Контакты - Мобильный телефон":
                element = phone;
                break;
            case "Контакты - Электронная почта":
                element = email;
                break;
            case "Контакты - Повтор электронной почты":
                element = repeatEmail;
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Оформления страхования путешественников'");

        }
        element = element.findElement(By.xpath("./..//span"));
        Assert.assertEquals("Проверка ошибки у поля '" + nameField + "' была не пройдена",
                errMassage, element.getText());
        return this;
    }

    /**
     * Клик по кнопке "Продолжить"
     *
     * @return RegistrationFormPage - т.е. остаемся на этой странице
     */
    public RegistrationFormPage clickBtnContinue() {
        waitUtilElementToBeClickable(btnContinue).click();
        return this;
    }

    /**
     * Проверка общей ошибки
     *
     * @return RegistrationFormPage - т.е. остаемся на этой странице
     */
    public RegistrationFormPage checkErrorMessageAlert(String errMessage) {
        Assert.assertEquals("Проверка ошибки у alert на странице " +
                        "'Оформления страхования путешественников' было не пройдено",
                errMessage, errorMessageAlert.getText());
        return this;
    }
}
