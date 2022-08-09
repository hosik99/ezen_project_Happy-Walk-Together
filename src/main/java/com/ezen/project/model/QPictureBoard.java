package com.ezen.project.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPictureBoard is a Querydsl query type for PictureBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPictureBoard extends EntityPathBase<PictureBoard> {

    private static final long serialVersionUID = -1181357147L;

    public static final QPictureBoard pictureBoard = new QPictureBoard("pictureBoard");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> bno = createNumber("bno", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath title = createString("title");

    public QPictureBoard(String variable) {
        super(PictureBoard.class, forVariable(variable));
    }

    public QPictureBoard(Path<? extends PictureBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPictureBoard(PathMetadata metadata) {
        super(PictureBoard.class, metadata);
    }

}

