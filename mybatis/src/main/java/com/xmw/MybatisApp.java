package com.xmw;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xmw.bean.User;
import com.xmw.dao.UserDao;

/**
 * mybatis 源码学习
 */
public class MybatisApp {
    public static void main(String[] args) throws IOException {

        // 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 获取sqlSession, sqlSession委托Executor去执行具体的sql
//        SqlSession sqlSession = sqlSessionFactory.openSession(false);
//        try {
//            Map<String, Object> param = new HashMap<>(1);
//            param.put("userId", "111");
//            //sqlSession.selectList要详细分析的方法
//            List<User> list = sqlSession.selectList("com.xmw.user.selectUserDetail", param);
//            System.out.println(list);
//            sqlSession.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }

//        mapperTest(sqlSessionFactory);

        // 测试映射到xml中的方法
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Configuration configuration = sqlSession.getConfiguration();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        Map<String, Object> param = new HashMap<>(1);
        param.put("userId", "111");
        List<User> list = mapper.selectUserDetail(param);
        mapper.queryUser(param);
        mapper.queryUserByProvider(param);
        System.out.println(list);
        sqlSession.commit();
    }

    public static void mapperTest(SqlSessionFactory sqlSessionFactory) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            System.out.println(userDao);
            Map<String, Object> param = new HashMap<>(16);
            param.put("userId", "111");
            List<Map<String, Object>> list = userDao.queryUserByProvider(param);
            List<Map<String, Object>> maps = userDao.queryUser(param);
            System.out.println(list);
            System.out.println(maps);
        }
    }
}
