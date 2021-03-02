package com.nowcoder.wenda;

import com.nowcoder.wenda.util.MailClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = WendaApplication.class)
public class MailTest {
    @Autowired
    private MailClient maliClient;

    @Test
    public void sendmailtest() {
        maliClient.sendMail("1571737664@qq.com", "test01", "hello");
    }



}
