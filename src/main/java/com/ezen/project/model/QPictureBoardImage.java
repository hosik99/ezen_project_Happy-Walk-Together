package com.ezen.project.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPictureBoardImage is a Querydsl query type for PictureBoardImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPictureBoardImage extends EntityPathBase<PictureBoardImage> {

    private static final long serialVersionUID = 472665270L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPictureBoardImage pictureBoardImage = new QPictureBoardImage("pictureBoardImage");

    public final QPictureBoard board;

    public final StringPath imgName = createString("imgName");

    public final NumberPath<Long> inum = createNumber("inum", Long.class);

    public final StringPath path = createString("path");

    public final StringPath uuid = createString("uuid");

    public QPictureBoardImage(String variable) {
        this(PictureBoardImage.class, forVariable(variable), INITS);
    }

    public QPictureBoardImage(Path<? extends PictureBoardImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPictureBoardImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPictureBoardImage(PathMetadata metadata, PathInits inits) {
        this(PictureBoardImage.class, metadata, inits);
    }

    public QPictureBoardImage(Class<? extends PictureBoardImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QPictureBoard(forProperty("board")) : null;
    }

}

