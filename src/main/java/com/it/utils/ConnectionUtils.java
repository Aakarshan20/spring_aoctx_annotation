package com.it.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 連接的工具類 用於從數據源中獲取一個連接 並且實現和線程的綁定
 */
@Component("connectionUtils")
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();


    @Autowired
    private DataSource dataSource;


    /**
     * 獲取當前線程上的一個連接
     * @return
     */
    public Connection getThreadConnection(){
        //1.從ThreadLocal上獲取
        Connection conn = tl.get();
        //2.判斷當前線程上是否有連接
        try {
            if (conn == null) {
                //3.從數據源中獲取一個連接 並且和線程綁定 存入ThreadLocal中
                conn = dataSource.getConnection();
                //4.conn存入 ThreadLocal中
                tl.set(conn);
            }
            //返回線程上的連接
            return conn;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 把連接和線程解綁
     */
    public void removeConnection(){
        tl.remove();
    }
}
