package com.nowcoder.wenda.dao;

import org.springframework.stereotype.Repository;


/**
 * 这里的注解repository有什么作用:访问数据库Bean.加上名字，可以指定访问这个实现类
 */
@Repository("alphahibernate")
public class AlphaDaohibernateImpl implements AlphaDao{
    @Override
    public String select() {
        return "hibernate";
    }
}
