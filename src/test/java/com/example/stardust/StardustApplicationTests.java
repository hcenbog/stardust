package com.example.stardust;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StardustApplicationTests {
    @Autowired(required = false)
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }
    /**
     ** 数据库连接池
     * com.mysql.cj.jdbc.ConnectionImpl@1f6c4ae
     */

    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
