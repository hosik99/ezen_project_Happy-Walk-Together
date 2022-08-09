package com.ezen.project.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMainBoard is a Querydsl query type for MainBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMainBoard extends EntityPathBase<MainBoard> {

    private static final long serialVersionUID = 287893168L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMainBoard mainBoard = new QMainBoard("mainBoard");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QMember mainBoardAuthor;

    public final StringPath mainBoardContents = createString("mainBoardContents");

    public final StringPath mainBoardLocation = createString("mainBoardLocation");

    public final NumberPath<Long> mainBoardNum = createNumber("mainBoardNum", Long.class);

    public final StringPath mainBoardTitle = createString("mainBoardTitle");

    public final DatePath<java.sql.Date> mainBoardWdate = createDate("mainBoardWdate", java.sql.Date.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final ListPath<PetImage, QPetImage> petImages = this.<PetImage, QPetImage>createList("petImages", PetImage.class, QPetImage.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QMainBoard(String variable) {
        this(MainBoard.class, forVariable(variable), INITS);
    }

    public QMainBoard(Path<? extends MainBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMainBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMainBoard(PathMetadata metadata, PathInits inits) {
        this(MainBoard.class, metadata, inits);
    }

    public QMainBoard(Class<? extends MainBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mainBoardAuthor = inits.isInitialized("mainBoardAuthor") ? new QMember(forProperty("mainBoardAuthor"), inits.get("mainBoardAuthor")) : null;
    }

}

