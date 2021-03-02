package com.nowcoder.wenda.controller;

import com.nowcoder.wenda.entity.Comment;
import com.nowcoder.wenda.entity.DiscussPost;
import com.nowcoder.wenda.entity.Page;
import com.nowcoder.wenda.entity.User;
import com.nowcoder.wenda.service.CommentService;
import com.nowcoder.wenda.service.DiscussPostService;
import com.nowcoder.wenda.service.LikeService;
import com.nowcoder.wenda.service.UserService;
import com.nowcoder.wenda.util.CommunityConstant;
import com.nowcoder.wenda.util.CommunityUtil;
import com.nowcoder.wenda.util.HostHolder;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController implements CommunityConstant {

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeService likeService;

    //新增贴子
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addDiscussPost(String title, String content) throws Exception {
        User user = hostHolder.getUser();
        if (user == null) {
            return CommunityUtil.getJSONString(403, "尚未登录！");
        }

        //处理贴子初始状态
        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());

        //发布
        discussPostService.addDiscussPost(post);

        return CommunityUtil.getJSONString(0, "发布成功！");
    }

    //展现贴子详情
    @RequestMapping(path = "/detail/{discussPostId}", method = RequestMethod.GET)
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model, Page page) {
        //贴子
        DiscussPost post = discussPostService.findDiscussPostById(discussPostId);
        model.addAttribute("post", post);
        //用户
        User user = userService.findUserById(post.getUserId());
        model.addAttribute("user", user);

        // 点赞数量
        long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, discussPostId);
        model.addAttribute("likeCount", likeCount);
        // 点赞状态
        int likeStatus = hostHolder.getUser() == null ? 0 :
                likeService.findEntityLikeStatus(hostHolder.getUser().getId(), ENTITY_TYPE_POST, discussPostId);
        model.addAttribute("likeStatus", likeStatus);



        //评论分页信息
        page.setLimit(5);
        page.setPath("/discuss/detail/" + discussPostId);
        page.setRows(post.getCommentCount());

        //评论：给贴子的评论
        //恢复：给评论的评论

        //评论列表
        List<Comment> commentList = commentService.findCommentByEntity(
                ENTITY_TYPE_POST, post.getId(), page.getOffset(), page.getLimit());
        //评论VO列表
        List<Map<String, Object>> commentVolist = new ArrayList<>();
        if (commentList != null) {
            for (Comment comment : commentList) {
                //评论VO（view object）
                Map<String, Object> commentVo = new HashMap<>();
                //评论
                commentVo.put("comment", comment);
                //作者
                commentVo.put("user", userService.findUserById(comment.getUserId()));
                // 点赞数量
                likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_COMMENT, comment.getId());
                commentVo.put("likeCount", likeCount);
                // 点赞状态
                likeStatus = hostHolder.getUser() == null ? 0 :
                        likeService.findEntityLikeStatus(hostHolder.getUser().getId(), ENTITY_TYPE_COMMENT, comment.getId());
                commentVo.put("likeStatus", likeStatus);

                //回复  回复列表 不需要分页，设置为0~max
                List<Comment> replayList = commentService.findCommentByEntity(
                        ENTITY_TYPE_COMMENT, comment.getEntityId(), 0, Integer.MAX_VALUE);
                //回复Vo列表
                List<Map<String,Object>> replayVoList=new ArrayList<>();
                if (replayList != null) {
                    for (Comment replay : replayList) {
                        Map<String, Object> replayVo = new HashMap<>();
                        //回复
                        replayVo.put("replay", replay);
                        //作者
                        replayVo.put("user", userService.findUserById(replay.getUserId()));
                        //target 回复评论指向
                        User target = replay.getTargetId() == 0 ? null : userService.findUserById(replay.getTargetId());
                        replayVo.put("target", target);
                        // 点赞数量
                        likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_COMMENT, replay.getId());
                        replayVo.put("likeCount", likeCount);
                        // 点赞状态
                        likeStatus = hostHolder.getUser() == null ? 0 :
                                likeService.findEntityLikeStatus(hostHolder.getUser().getId(), ENTITY_TYPE_COMMENT, replay.getId());
                        replayVo.put("likeStatus", likeStatus);



                        replayVoList.add(replayVo);
                    }
                }
                //回复
                commentVo.put("replays",replayVoList);

                //回复数量
                int replayCount=commentService.findCountByEntity(ENTITY_TYPE_COMMENT,comment.getId());
                commentVo.put("replayCount",replayCount);

                commentVolist.add(commentVo);
            }

        }

        //装进模板
        model.addAttribute("comments",commentVolist);

        return "/site/discuss-detail";
    }
}
