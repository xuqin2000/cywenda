package com.nowcoder.wenda.controller;

import com.nowcoder.wenda.entity.DiscussPost;
import com.nowcoder.wenda.entity.Page;
import com.nowcoder.wenda.entity.User;
import com.nowcoder.wenda.service.DiscussPostService;
import com.nowcoder.wenda.service.LikeService;
import com.nowcoder.wenda.service.UserService;
import com.nowcoder.wenda.util.CommunityConstant;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller

public class HomeController implements CommunityConstant {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    //post中的userid，对应user中的id。取出username
    //组成新的map，返回展现
    @RequestMapping(path = "/index", method = RequestMethod.GET)

    public String getIndexPage(Model model, Page page) {

        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();//多个map放在list中，展现到页面
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();//新的map来装post与user信息
                map.put("post", post);

                User user = userService.findUserById(post.getUserId());
                map.put("user", user);

                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId());
                map.put("likeCount", likeCount);

                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        return "/index";
    }

}
