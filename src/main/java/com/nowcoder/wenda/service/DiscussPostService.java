package com.nowcoder.wenda.service;

import com.nowcoder.wenda.dao.DiscussPostMapper;
import com.nowcoder.wenda.entity.DiscussPost;
import com.nowcoder.wenda.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class DiscussPostService {
    @Autowired(required = false)
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;


    //主页贴子展示
    public List<DiscussPost> findDiscussPosts(int userId,int offset,int limit){
        return discussPostMapper.selectDiscussPosts(userId,offset,limit);
    }

    public int findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }

    //增加贴子
    public int addDiscussPost(DiscussPost post){
        if (post==null){
            throw new IllegalArgumentException("参数不能为空");
        }

        //转义html标记
        post.setTitle(HtmlUtils.htmlEscape(post.getTitle()));
        post.setContent(HtmlUtils.htmlEscape(post.getContent()));
        //过滤敏感词
        post.setTitle(sensitiveFilter.filter(post.getTitle()));
        post.setContent(sensitiveFilter.filter(post.getContent()));
        return discussPostMapper.insertDiscussPost(post);
    }

    //贴子详情
    public DiscussPost findDiscussPostById(int id){
        return discussPostMapper.selectDiscussPostById(id);
    }


    //修改贴子回复数量
    public int updateCommentCount(int id,int CommentCount){
        return discussPostMapper.updateCommentCount(id,CommentCount);
    }
}

