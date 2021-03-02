package com.nowcoder.wenda.service;

import com.nowcoder.wenda.dao.MessageMapper;
import com.nowcoder.wenda.entity.Message;
import com.nowcoder.wenda.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class MessageService {
    @Autowired(required = false)
    private MessageMapper messageMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    //查询所有会话
    public List<Message> findConversations(int userId, int offset, int limit) {
        return messageMapper.selectConversations(userId, offset, limit);
    }

    //查询会话数量
    public int findConversationCount(int userId) {
        return messageMapper.selectConversationCount(userId);
    }

    //查询某个会话所包含的私信列表
    public List<Message> findLetters(String conversationId, int offset, int limit) {
        return messageMapper.selectLetters(conversationId, offset, limit);
    }

    //查询某个会话所包含的私信数量
    public int findLetterCount(String conversationId) {
        return messageMapper.selectLetterCount(conversationId);
    }

    //查询未读私信的数量(全部)
    public int findLetterUnreadCount(int userId, String conversationId) {
        return messageMapper.selectLetterUnreadCount(userId, conversationId);
    }

    //增加私信
    public int addMessage(Message message) {
        message.setContent(HtmlUtils.htmlEscape(message.getContent()));
        message.setContent(sensitiveFilter.filter(message.getContent()));
        return messageMapper.insertMessage(message);
    }

    //消息已读
    public int readMessage(List<Integer> ids){
        return messageMapper.updateStatus(ids,1);
    }

    //查询最新通知
    public Message findLatestNotice(int userId, String topic) {
        return messageMapper.selectLatestNotice(userId, topic);
    }

    //查询最新通知数量
    public int findNoticeCount(int userId, String topic) {
        return messageMapper.selectNoticeCount(userId, topic);
    }

    //查询未读通知信息数量
    public int findNoticeUnreadCount(int userId, String topic) {
        return messageMapper.selectNoticeUnreadCount(userId, topic);
    }

    //查询未读通知信息
    public List<Message> findNotices(int userId, String topic, int offset, int limit) {
        return messageMapper.selectNotices(userId, topic, offset, limit);
    }


}
