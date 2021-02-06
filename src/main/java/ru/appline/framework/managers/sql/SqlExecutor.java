package ru.appline.framework.managers.sql;

import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для выполнения Sql-запросов
 */
public class SqlExecutor {

    /**
     * JDBC Менеджер, переменная для хранения объекта менеджера управляющий соединением БД
     *
     * @see JdbcManager#getJDBCManager()
     */
    final JdbcManager jdbcManager = JdbcManager.getJDBCManager();

    /**
     * Переменна для хранения объекта SqlExecutor
     */
    private static SqlExecutor INSTANCE = null;

    /**
     * Переменная в которую сохраняется результат Sql-запроса
     */
    private final StringBuilder sqlResult = new StringBuilder();

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     * Нужен для инициализации переменной {@link #INSTANCE}
     *
     * @see SqlExecutor#getSqlExecutor()
     */
    private SqlExecutor() {
    }

    /**
     * Метод ленивой инициализации SqlExecutor
     *
     * @return SqlExecutor - возвращает SqlExecutor
     */
    public static SqlExecutor getSqlExecutor() {
        if (INSTANCE == null) {
            INSTANCE = new SqlExecutor();
        }
        return INSTANCE;
    }

    /**
     * Метод выполняющий sql запрос и преобразовывающий данный запрос в формат json массива
     * jsonArray формат нужен для дальнейшей проверки результатов из БД
     *
     * @param sqlRequest - специальный объект, который нужен для установки самого sql и его параметров
     * @return String - результат sql запроса в виде jsonArray
     * @see SqlRequest
     * @see SqlParameter
     * @see SqlParamType
     */
    public String execute(SqlRequest sqlRequest) {
        sqlResult.setLength(0);
        try {
            PreparedStatement preparedStatement = jdbcManager.getConnection().prepareStatement(sqlRequest.getSqlRequest());
            sqlRequest.fillWithParamsSqlRequest(preparedStatement, sqlRequest.getSqlParameters());
            if (preparedStatement.execute()) {
                ResultSet resultSet = preparedStatement.getResultSet();
                sqlResult.append(getFormattedResult(resultSet).toString());
            } else {
                sqlResult.append(preparedStatement.getUpdateCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcManager.closeConnection();
        }
        return sqlResult.toString();
    }

    /**
     * Метод форматирует каждую строку результата в JSONObject и помещает все JSONObject в ArrayList,
     * тем самым будет сформирован массив из JSONObject, который в конечном итоги можно преобразовать в JSONArray
     *
     * @param rs - набор данных (строк) из БД после выполнения запроса
     * @return List<JSONObject> - возвращает лист JSONObject
     * @see SqlExecutor#execute(SqlRequest)
     */
    private List<JSONObject> getFormattedResult(ResultSet rs) {
        List<JSONObject> resList = new ArrayList<>();
        try {
            // get column names
            ResultSetMetaData rsMeta = rs.getMetaData();
            String key;
            String strValue;
            while (rs.next()) { // convert each object to an human readable JSON object
                JSONObject obj = new JSONObject();
                for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
                    key = rsMeta.getColumnName(i);
                    strValue = rs.getString(i);
                    switch (rsMeta.getColumnClassName(i)) {
                        case "java.lang.Long":
                        case "java.lang.Integer":
                        case "java.lang.Short":
                        case "java.lang.Byte":
                            obj.put(key, strValue == null ? JSONObject.NULL : rs.getLong(i));
                            break;
                        case "java.lang.Boolean":
                            obj.put(key, strValue == null ? JSONObject.NULL : rs.getBoolean(i));
                            break;
                        case "java.lang.Float":
                        case "java.lang.Double":
                            obj.put(key, strValue == null ? JSONObject.NULL : rs.getDouble(i));
                            break;
                        default:
                            obj.put(key, strValue == null ? JSONObject.NULL : strValue);
                    }
                }
                resList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return resList;
    }


}
