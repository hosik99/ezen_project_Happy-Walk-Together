package com.ezen.project.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMessage is a Querydsl query type for Message
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMessage extends EntityPathBase<Message> {

    private static final long serialVersionUID = 222553802L;

    public static final QMessage message = new QMessage("message");

    public final StringPath contents = createString("contents");

    public final NumberPath<Long> num = createNumber("num", Long.class);

    public final NumberPath<Integer> readed = createNumber("readed", Integer.class);

    public final StringPath receiver = createString("receiver");

    public final StringPath sender = createString("sender");

    public final StringPath title = createString("title");

    public final DatePath<java.sql.Date> writeDate = createDate("writeDate", java.sql.Date.class);

    public QMessage(String variable) {
        super(Message.class, forVariable(variable));
    }

    public QMessage(Path<? extends Message> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMessage(PathMetadata metadata) {
        super(Message.class, metadata);
    }

}

