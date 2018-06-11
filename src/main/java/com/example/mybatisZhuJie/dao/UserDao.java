package com.example.mybatisZhuJie.dao;

import com.example.mybatisZhuJie.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface UserDao {
    @Select("select id, user_name ,password, age from user_t")
    public ArrayList<User> selectAll();

    @Insert("insert into user_t(id, user_name ,password, age) values(#{id},#{user_name}, #{password}, #{age})")
    public int insert(User user);

    @Delete("delete from user_t where id =#{id}")
    public int delete(@Param("id") int id);

    //Sql语句进行分页
    @Select("select id, user_name ,password, age from user_t limit #{currPage}, #{pageSize}")
    public ArrayList<User> queryStudentsBySql(@Param("currPage") int currPage, @Param("pageSize") int pageSize);
}
