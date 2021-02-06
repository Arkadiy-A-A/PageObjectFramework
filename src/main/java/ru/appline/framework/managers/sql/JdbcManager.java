package ru.appline.framework.managers.sql;

import org.junit.Assert;
import ru.appline.framework.managers.TestPropManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ru.appline.framework.utils.PropConst.*;

/**
 * @author Arkadiy_Alaverdyan
 * Класс управляющий соединением БД
 */
class JdbcManager {

    /**
     * Менеджер properties
     *
     * @see TestPropManager#getTestPropManager()
     */
    private final TestPropManager props = TestPropManager.getTestPropManager();

    /**
     * Переменна для хранения объекта JdbcManager
     */
    private static JdbcManager INSTANCE = null;

    /**
     * Переменна для хранения подключения к БД
     *
     * @see DriverManager - класс для получения конекта к БД
     */
    private Connection connection;

    private String url;
    private final String dbHost = props.getProperty(DB_HOST);
    private final String dbPort = props.getProperty(DB_PORT);
    private final String dbSchemaName = props.getProperty(DB_SCHEMA_NAME);

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     * Нужен для инициализации переменной {@link #INSTANCE}
     *
     * @see JdbcManager#getJDBCManager()
     */
    private JdbcManager() {
    }

    /**
     * Метод ленивой инициализации JdbcManager
     * Доступен только в рамках пакета sql - спрятан специально
     * <p>
     * Метод закрыт от пользователя так как используется в {@link SqlExecutor#jdbcManager}
     *
     * @return JdbcManager - возвращает JdbcManager
     * @see SqlExecutor#execute(SqlRequest)
     */
    static JdbcManager getJDBCManager() {
        if (INSTANCE == null) {
            INSTANCE = new JdbcManager();
        }
        return INSTANCE;
    }

    /**
     * Метод для настройки всех нужных параметров необходимых для подключения к самым основным СУБД
     * Инициализирует переменную {@link #connection}
     *
     * Метод закрыт от пользователя так как используется в {@link SqlExecutor#execute(SqlRequest)}
     *
     * @return Connection - переменную которая хранится в данном менеджере
     */
    Connection getConnection() {
        if (connection == null) {
            try {
                switch (props.getProperty(DB_TYPE)) {
                    case "mysql":
                        url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbSchemaName + "?serverTimezone=" + props.getProperty(DB_TIME_ZONE);
                        break;
                    case "oracle":
                        url = "jdbc:oracle:thin:@" + dbHost + ":" + dbPort + ":" + dbSchemaName;
                        break;
                    case "postgresql":
                        url = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbSchemaName;
                        break;
                    default:
                        Assert.fail("Настроек для подключения к БД '" + props.getProperty(DB_TYPE) + "'" +
                                " отсутствуют во framework.");
                }
                System.out.println(url);
                System.out.println(props.getProperty(DB_USER_LOGIN));
                System.out.println(props.getProperty(DB_USER_PASSWORD));
                connection = DriverManager.getConnection(url, props.getProperty(DB_USER_LOGIN), props.getProperty(DB_USER_PASSWORD));
                connection.setClientInfo("AutotestUser", "JDBC Framework");
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * Метод который нужен для закрытия соединения с БД
     * Сбрасывает значение хранящееся в переменной {@link #connection}
     *
     * Метод закрыт от пользователя так как используется в {@link SqlExecutor#execute(SqlRequest)}
     */
    void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

