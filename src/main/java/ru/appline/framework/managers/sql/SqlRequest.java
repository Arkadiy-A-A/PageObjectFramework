package ru.appline.framework.managers.sql;

import org.junit.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.appline.framework.managers.sql.SqlParamType.*;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для формирования и сохранения Sql-запросов и их параметров
 */
public class SqlRequest {

    private final String sqlRequest;

    private final List<SqlParameter> sqlParameters;

    public SqlRequest(String sqlRequest) {
        this(sqlRequest, new ArrayList<>());
    }

    private SqlRequest(String sqlRequest, List<SqlParameter> sqlParameters) {
        this.sqlRequest = sqlRequest;
        this.sqlParameters = sqlParameters;
    }

    String getSqlRequest() {
        return sqlRequest;
    }

    SqlParameter[] getSqlParameters() {
        return sqlParameters.toArray(new SqlParameter[0]);
    }

    public SqlRequest setParam(int value) {
        this.sqlParameters.add(new SqlParameter(INTEGER, String.valueOf(value)));
        return this;
    }

    public SqlRequest setParam(long value) {
        this.sqlParameters.add(new SqlParameter(LONG, String.valueOf(value)));
        return this;
    }

    public SqlRequest setParam(double value) {
        this.sqlParameters.add(new SqlParameter(DOUBLE, String.valueOf(value)));
        return this;
    }

    public SqlRequest setParam(float value) {
        this.sqlParameters.add(new SqlParameter(FLOAT, String.valueOf(value)));
        return this;
    }

    public SqlRequest setParam(boolean value) {
        this.sqlParameters.add(new SqlParameter(BOOLEAN, String.valueOf(value)));
        return this;
    }

    public SqlRequest setParam(String value) {
        this.sqlParameters.add(new SqlParameter(STRING, value));
        return this;
    }

    void fillWithParamsSqlRequest(PreparedStatement preparedStatement, SqlParameter... requestParams) {
        int i = 0;
        String paramValue = null;
        try {
            for (SqlParameter paramItem : requestParams) {
                paramValue = paramItem.getValue();
                switch (paramItem.getSqlParamType()) {
                    case STRING:
                        preparedStatement.setString(++i, paramValue);
                        break;
                    case FLOAT:
                    case DOUBLE:
                        preparedStatement.setDouble(++i, Double.parseDouble(paramValue));
                        break;
                    case LONG:
                    case INTEGER:
                        preparedStatement.setLong(++i, Long.parseLong(paramValue));
                        break;
                    case BOOLEAN:
                        preparedStatement.setBoolean(++i, Boolean.parseBoolean(paramValue));
                        break;
                    case DATE:
                        preparedStatement.setDate(++i, Date.valueOf(paramValue));
                        break;
                    case TIME:
                        preparedStatement.setTime(++i, Time.valueOf(paramValue));
                        break;
                    case TIMESTAMP:
                        preparedStatement.setTimestamp(++i, Timestamp.valueOf(paramValue));
                        break;
                    case UUID:
                        preparedStatement.setObject(++i, java.util.UUID.fromString(paramValue));
                        break;
                    default:
                        Assert.fail("Unexpected value: " + paramItem.getSqlParamType());
                }
            }
        } catch (SQLException e) {
            Assert.fail("SQL prepare parameters error\n" +
                    "\nПараметр: " + paramValue +
                    "\nStatement: " + preparedStatement);
            e.printStackTrace();
        }
    }
}
