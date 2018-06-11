package com.example.mybatisZhuJie;


import com.example.mybatisZhuJie.tool.MybatisMysql;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Reader;

@SpringBootApplication
public class AppZhuJie {

	public static MybatisMysql mybatisMysqlPool;
	private static String env = "local";

	public static void main(String[] args)  throws Exception{
		new AppZhuJie().init();
		SpringApplication.run(AppZhuJie.class, args);
	}
	public void init() throws Exception {
		initMysql();
	}
	private void initMysql() {
		MybatisMysql mysqlPool = MybatisMysql.getInstance();
		String resource = String.format("mybatis-config-%s.xml", AppZhuJie.env);
		Reader masterReader = null;
		//Reader slaveReader = null;
		try {
			masterReader = Resources.getResourceAsReader(resource);
			//slaveReader = Resources.getResourceAsReader(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			//设置主库连接池
			mysqlPool.setMasterPool(builder.build(masterReader, "master"));
		/*	List<SqlSessionFactory> slavePoolList = new ArrayList<SqlSessionFactory>();
			slavePoolList.add(builder.build(slaveReader, "slave"));
			//从库连接池
			mysqlPool.setSlavePool(slavePoolList);*/
			AppZhuJie.mybatisMysqlPool = mysqlPool;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
