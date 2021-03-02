package com.nowcoder.wenda.util;

//常量接口
public interface CommunityConstant {
    /**
     * 激活成功
     */
    int ACTVATION_SUCCESS = 0;

    /**
     * 重复激活
     */
    int ACTVATION_REPEAT = 1;

    /**
     * 激活失败
     */
    int ACTVATION_FAILURE = 2;

    /**
     * 默认状态的登录凭证的超时时间
     */
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;//12H

    /**
     * 记住我状态的登录凭证超时时间
     */
    int REMEMBER_EXPIERD_SECONDS = 3600 * 24 * 30;

    /**
     * 实体类型：帖子(对于贴子的评论类型为1)
     */
    int ENTITY_TYPE_POST = 1;

    /**
     * 实体类型：评论（对于评论的评论类型为2）
     */
    int ENTITY_TYPE_COMMENT = 2;

    /**
     * 实体类型：用户
     */
    int ENTITY_TYPE_USER = 3;

    /**
     * 主题：评论
     */
    String TOPIC_COMMENT = "comment";

    /**
     * 主题：点赞
     */
    String TOPIC_LIKE = "like";

    /**
     * 主题：关注
     */
    String TOPIC_FOLLOW = "follow";

    /**
     * 系统ID
     */
    int SYSTEM_USER_ID = 1;
}
