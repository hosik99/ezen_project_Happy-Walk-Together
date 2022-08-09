package com.ezen.project.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatChannel is a Querydsl query type for ChatChannel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatChannel extends EntityPathBase<ChatChannel> {

    private static final long serialVersionUID = 1035164622L;

    public static final QChatChannel chatChannel = new QChatChannel("chatChannel");

    public final StringPath channelCode = createString("channelCode");

    public final StringPath channelTitle = createString("channelTitle");

    public final NumberPath<Long> chatNum = createNumber("chatNum", Long.class);

    public final DatePath<java.sql.Date> createDate = createDate("createDate", java.sql.Date.class);

    public final NumberPath<Integer> readed = createNumber("readed", Integer.class);

    public final StringPath userId = createString("userId");

    public QChatChannel(String variable) {
        super(ChatChannel.class, forVariable(variable));
    }

    public QChatChannel(Path<? extends ChatChannel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatChannel(PathMetadata metadata) {
        super(ChatChannel.class, metadata);
    }

}

