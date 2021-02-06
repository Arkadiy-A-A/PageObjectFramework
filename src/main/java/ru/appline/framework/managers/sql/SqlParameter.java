package ru.appline.framework.managers.sql;

import lombok.Getter;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для создания Sql-парметров в Sql-запросе
 */
class SqlParameter {

    @Getter
    private final SqlParamType sqlParamType;

    @Getter
    private final String value;

    SqlParameter(SqlParamType sqlParamType, String value) {
        this.sqlParamType = sqlParamType;
        this.value = value;
    }

}
