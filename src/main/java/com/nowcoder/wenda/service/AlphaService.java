package com.nowcoder.wenda.service;

import com.nowcoder.wenda.dao.AlphaDao;
import com.nowcoder.wenda.dao.AlphaDaoMyBatisImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * service方法，使用的service注解
 */
@Service
//@Scope("prototype")//此注解可以创建多实例
public class AlphaService {

    @Autowired //注入dao，访问数据库
    private AlphaDao alphaDao;

    public String find(){
        return alphaDao.select();
    }

    public AlphaService(){
        System.out.println("实例化此方法");
    }
    @PostConstruct //什么意思
    public void init(){
        System.out.println("初始化此方法");
    }

    @PreDestroy //mean?
    public void destroy(){
        System.out.println("销毁此方法");
    }

}
