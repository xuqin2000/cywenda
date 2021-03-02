package com.nowcoder.wenda.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


@Repository
@Primary //优先访问
public class AlphaDaoMyBatisImpl implements AlphaDao {
    @Override
    public String select() {
        return "MyBatis";
    }
}
