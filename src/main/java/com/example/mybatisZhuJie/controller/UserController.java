package com.example.mybatisZhuJie.controller;

import com.example.mybatisZhuJie.AppZhuJie;
import com.example.mybatisZhuJie.dao.UserDao;
import com.example.mybatisZhuJie.dao.UserDaoIml;
import com.example.mybatisZhuJie.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    UserDao userDao = new UserDaoIml(AppZhuJie.mybatisMysqlPool.getMasterPool());

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRegTimeByUserId (HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        String data = request.getParameter("data");
        result.put("data",data);
        result.put("code","200");
        return result;
    }


    @RequestMapping(value = "/select/all", method = RequestMethod.GET)
    @ResponseBody
    public List<User> selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<User> users = userDao.selectAll();
        return users;
    }

    @RequestMapping(value = "/select/some", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<User> selectSome(HttpServletRequest request, HttpServletResponse response
                    ,@RequestParam(value = "start", defaultValue = "0") int start
                    ,@RequestParam(value = "end", defaultValue = "3") int end) throws Exception{
        ArrayList<User> users = userDao.queryStudentsBySql(start, end);
        return users;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public  String delete(@RequestParam(value = "id", defaultValue = "6") int id) {
        int count = userDao.delete(id);
        return "成功删除" + count + "条数据！";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    @ResponseBody
    public  String insert(@RequestParam(value = "id", defaultValue = "6") int id
            , @RequestParam(value = "username", defaultValue = "易强强")String username
            , @RequestParam(value = "password", defaultValue = "yiqq")String password
            , @RequestParam(value = "age", defaultValue = "22") int age) {
        User user = new User();
        user.setId(id);
        user.setAge(age);
        user.setPassword(password);
        user.setUser_name(username);
        int count = userDao.insert(user);
        return "成功插入" + count + "条数据！";
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public  String say(@RequestParam(value = "name", defaultValue = "易强强") String name) {
        return "Hello World: " + name;
    }

}
