package com.nowcoder.wenda;

import com.nowcoder.wenda.dao.*;
import com.nowcoder.wenda.entity.DiscussPost;
import com.nowcoder.wenda.entity.LoginTicket;
import com.nowcoder.wenda.entity.Message;
import com.nowcoder.wenda.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@SpringBootTest
@ContextConfiguration(classes = WendaApplication.class)
public class MapperTest {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private DiscussPostMapper discussPostMapper;

    @Autowired(required = false)
    private LoginTicketMapper loginTicketMapper;

    @Autowired(required = false)
    private MessageMapper messageMapper;

    @Test
    public void testSelectUser() {
        User user = userMapper.selectById(101);
        System.out.println(user);

        user = userMapper.selectByName("liubei");
        System.out.println(user);

        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void updateUser() {
        int rows = userMapper.updateStatus(150, 1);
        System.out.println(rows);

        rows = userMapper.updateHeader(150, "http://www.nowcoder.com/102.png");
        System.out.println(rows);

        rows = userMapper.updatePassword(150, "hello");
        System.out.println(rows);
    }


    @Test
    public void testSelectPosts() {
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for (DiscussPost post : list
        ) {
            System.out.println(post);
        }
        int rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
    }

    @Test
    public void testInsertLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(001);
        loginTicket.setTicket("abcc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));
        loginTicketMapper.insertloginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abcc");
        System.out.println(loginTicket);

        System.out.println("------------");

        loginTicketMapper.updateStatus("abcc", 1);
        loginTicket = loginTicketMapper.selectByTicket("abcc");
        System.out.println(loginTicket);
    }

    @Test
    public void testmessageMapper(){
         List<Message> messages = messageMapper.selectConversations(111, 0, 20);
        for (Message message:messages) {
            System.out.println(message);
        }
         int count = messageMapper.selectConversationCount(111);
        System.out.println(count);

         int letterCount = messageMapper.selectLetterCount("111_113");
        System.out.println(letterCount);

        List<Message> letters = messageMapper.selectLetters("111_113", 0, 10);
        for (Message letter: letters) {
            System.out.println(letter);
        }

         int unreadCount = messageMapper.selectLetterUnreadCount(111, "111_112");
        System.out.println(unreadCount);
    }
}
