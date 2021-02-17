package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	@Bean
    public SqlSessionFactory sqlSessionFactory (DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
    
    sqlSessionFactory.setDataSource(dataSource);
    sqlSessionFactory.setMapperLocations(
    			new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
    sqlSessionFactory.setTypeAliasesPackage("com.example.demo.dto");
    //sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
    return sqlSessionFactory.getObject();
}

	@Bean
	public SqlSessionTemplate sqlSession (SqlSessionFactory sqlSessionFactory) {
	    
	    return new SqlSessionTemplate(sqlSessionFactory);
	}

}
