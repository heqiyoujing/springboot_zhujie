package com.example.mybatisZhuJie.dao;

import com.example.mybatisZhuJie.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;

public class UserDaoIml implements UserDao {

    private SqlSessionFactory sessionFactory;

    public UserDaoIml(SqlSessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public ArrayList<User> selectAll() {
        ArrayList<User> users = new ArrayList<>();
        SqlSession sqlSession = null;
        try{
            sqlSession = sessionFactory.openSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            users = userDao.selectAll();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return users;
        }
    }

    public int insert(User user) {
        SqlSession sqlSession = null;
        int count = 0;
        try{
            sqlSession = sessionFactory.openSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            count = userDao.insert(user);
            if( count > 0) {
                sqlSession.commit();
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return count;
        }
    }

    public int delete(int id) {
        SqlSession sqlSession = null;
        int count = 0;
        try{
            sqlSession = sessionFactory.openSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            count = userDao.delete(id);
            if( count > 0) {
                sqlSession.commit();
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return count;
        }
    }

    public ArrayList<User> queryStudentsBySql(@Param("currPage") int currPage, @Param("pageSize") int pageSize) {
        ArrayList<User> users = new ArrayList<>();
        SqlSession sqlSession = null;
        try{
            sqlSession = sessionFactory.openSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            users = userDao.queryStudentsBySql(currPage, pageSize);
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return users;
        }
    }
}
