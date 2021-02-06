package ru.appline.framework.managers.sql;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для перечисления всех возможных типов параметров передаваемых в Sql-запросе
 */
enum SqlParamType {

    STRING("string"),
    DOUBLE("double"),
    FLOAT("float"),
    LONG("long"),
    INTEGER("integer"),
    BOOLEAN("boolean"),
    DATE("date"),
    TIME("time"),
    TIMESTAMP("timestamp"),
    UUID("uuid");

    private final String title;

    SqlParamType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "SqlParamType{" +
                "title='" + title + '\'' +
                '}';
    }
}
