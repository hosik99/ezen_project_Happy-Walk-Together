package com.ezen.project.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMateSearchBoard is a Querydsl query type for MateSearchBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMateSearchBoard extends EntityPathBase<MateSearchBoard> {

    private static final long serialVersionUID = -1647783524L;

    public static final QMateSearchBoard mateSearchBoard = new QMateSearchBoard("mateSearchBoard");

    public final StringPath mateAuthor = createString("mateAuthor");

    public final NumberPath<Long> mateBoardNum = createNumber("mateBoardNum", Long.class);

    public final StringPath mateContents = createString("mateContents");

    public final StringPath mateLocation = createString("mateLocation");

    public final StringPath mateTitle = createString("mateTitle");

    public final DatePath<java.sql.Date> mateWdate = createDate("mateWdate", java.sql.Date.class);

    public QMateSearchBoard(String variable) {
        super(MateSearchBoard.class, forVariable(variable));
    }

    public QMateSearchBoard(Path<? extends MateSearchBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMateSearchBoard(PathMetadata metadata) {
        super(MateSearchBoard.class, metadata);
    }

}

