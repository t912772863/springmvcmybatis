package jdbc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by tom on 2018/10/18.
 */
public class JdbcTest {
    public static void main(String[] args) throws SQLException {
        // json转对象
        JdbcTest j = JSON.toJavaObject(JSON.parseObject(""), JdbcTest.class);

        Connection connection = new JdbcTemplate().getDataSource().getConnection();
        connection.setAutoCommit(false);


        connection.commit();

        connection.setAutoCommit(true);
        connection.rollback();

    }

}
