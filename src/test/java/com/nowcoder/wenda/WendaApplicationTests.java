package com.nowcoder.wenda;

import com.nowcoder.wenda.config.AlphaConfig;
import com.nowcoder.wenda.dao.AlphaDao;
import com.nowcoder.wenda.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = WendaApplication.class)//这里的注解：以wenda类为配置类
class WendaApplicationTests implements ApplicationContextAware  {
    //实现ApplicationContextAware是为什么？得到该类的spring容器，
    //实现setApplicationContext这个方法，spring容器会检测到该类，扫描主键的时候，检测到bean，调用set方法把自身传进来

    private ApplicationContext applicationContext;//成员变量，记录applicationcontext 这是怎么来的
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext=applicationContext;
    }
    @Test
    public void  testApplicationContext(){
        System.out.println(applicationContext);

        //优先使用mybatis实现类
        AlphaDao alphaDao=applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao.select());
        //指定名字使用hibernate实现类
       AlphaDao alphaDao2=applicationContext.getBean("alphahibernate",AlphaDao.class);
        System.out.println(alphaDao2.select());
    }

    public void testBeanManagement(){
        //bean只被实例化一次，单例
        AlphaService alphaService= applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
    }


    @Test
    public void  testBeanConfig(){
        SimpleDateFormat simpleDateFormat=applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    @Autowired    //依赖注入
    @Qualifier("alphahibernate") //指定注入(不加的话注入的是默认优先的mybatis类)
    private AlphaDao alphaDao;

    @Autowired
    private AlphaService alphaService;

    @Autowired
    SimpleDateFormat simpleDateFormat;

    @Test
    public void testDi(){
        System.out.println(alphaDao);
        System.out.println(alphaService);
        System.out.println(simpleDateFormat);

    }

}
