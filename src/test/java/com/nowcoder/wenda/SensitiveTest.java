package com.nowcoder.wenda;

import com.nowcoder.wenda.util.SensitiveFilter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = WendaApplication.class)
public class SensitiveTest {
    @Autowired
   private SensitiveFilter sensitiveFilter;

    @Test
    public void setSensitiveFilterTest(){
        String text="我想吸毒";
        text = sensitiveFilter.filter(text);
        System.out.println(text);

        String text02="来▼吸◆毒，来■嫖▲娼，来☺玩☹啊~";
        text02=sensitiveFilter.filter(text02);
        System.out.println(text02);
    }
}
