package com.xmw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * mysql 创建connection测试
 * create connection: 351ms
 * exec sql: 36ms
 * 创建一个java.sql.Connection对象的代价是如此巨大，是因为创建一个Connection对象的过程，在底层就相当于和数据库建立的通信连接，
 * 在建立通信连接的过程，消耗了这么多的时间，而往往我们建立连接后（即创建Connection对象后），就执行一个简单的SQL语句，然后就要抛弃掉，
 * 这是一个非常大的资源浪费！
 */
public class TestConnection {
    public static void main(String[] args) throws Exception {
        String sql = "select user_id,user_name,user_type, user_pwd, svc_num,cust_id,acct_id from user a where a.user_id=?";
        PreparedStatement st = null;
        ResultSet rs = null;

        // 创建Connection对象前时间
        long beforeTimeOffset = -1L;
        // 创建Connection对象后时间
        long afterTimeOffset = -1L;
        // 创建Connection对象后时间
        long executeTimeOffset = -1L;

        Connection con = null;
        Class.forName("com.mysql.cj.jdbc.Driver");

        beforeTimeOffset = System.currentTimeMillis();
        System.out.println("before:\t" + beforeTimeOffset);

        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "mysql8.0");

        afterTimeOffset = System.currentTimeMillis();
        System.out.println("after:\t\t" + afterTimeOffset);
        System.out.println("Create Connection Costs:\t\t" + (afterTimeOffset - beforeTimeOffset) + " ms");

        st = con.prepareStatement(sql);
        // 设置参数
        st.setString(1, "111");
        // 查询，得出结果集
        rs = st.executeQuery();
        // 调用过close()方法的Connection对象所持有的资源会被全部释放掉，Connection对象也就不能再使用。
        con.close();
        executeTimeOffset = System.currentTimeMillis();
        System.out.println("Exec Costs:\t\t" + (executeTimeOffset - afterTimeOffset) + " ms");
    }
}
