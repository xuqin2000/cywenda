package com.nowcoder.wenda.dao;

import com.nowcoder.wenda.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);

    //分页显示
    //@Param注解用于给参数取名
    //如果只有一个参数，并且在<if>里使用，则必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);

    //增加贴子
    int insertDiscussPost(DiscussPost discussPost);

    //查询贴子详情
    DiscussPost selectDiscussPostById(int id);

    //修改回复count
    int updateCommentCount(int id,int commentCount);

}
