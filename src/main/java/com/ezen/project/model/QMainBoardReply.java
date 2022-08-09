package com.ezen.project.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMainBoardReply is a Querydsl query type for MainBoardReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMainBoardReply extends EntityPathBase<MainBoardReply> {

    private static final long serialVersionUID = 327084570L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMainBoardReply mainBoardReply = new QMainBoardReply("mainBoardReply");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QMainBoard mainboard;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath replyer = createString("replyer");

    public final NumberPath<Long> rno = createNumber("rno", Long.class);

    public final StringPath text = createString("text");

    public QMainBoardReply(String variable) {
        this(MainBoardReply.class, forVariable(variable), INITS);
    }

    public QMainBoardReply(Path<? extends MainBoardReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMainBoardReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMainBoardReply(PathMetadata metadata, PathInits inits) {
        this(MainBoardReply.class, metadata, inits);
    }

    public QMainBoardReply(Class<? extends MainBoardReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mainboard = inits.isInitialized("mainboard") ? new QMainBoard(forProperty("mainboard"), inits.get("mainboard")) : null;
    }

}

