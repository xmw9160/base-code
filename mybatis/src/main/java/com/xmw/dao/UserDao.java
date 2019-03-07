package com.xmw.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.xmw.bean.User;

@CacheNamespace
//@CacheNamespaceRef
public interface UserDao {

    /**
     * 使用注解定义执行的sql
     */
    @Select("select * from user where user_id = #{userId}")
    List<Map<String, Object>> queryUser(Map<String, ?> param);

    /**
     * 也可以这样子设置，Mybatis会调用UserSqlProvider.getQuerySql()方法获取要执行的sql
     */
    @SelectProvider(
            type = UserSqlProvider.class,
            // 方法返回值必须为String类型
            method = "getQuerySql"
    )
    List<Map<String, Object>> queryUserByProvider(Map<String, ?> param);

    class UserSqlProvider {
        public String getQuerySql() {
            return "select  * from user where user_id = #{userId}";
        }
    }

    List<User> selectUserDetail(Map<String, ?> param);
}
