package com.ezen.project.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberImage is a Querydsl query type for MemberImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberImage extends EntityPathBase<MemberImage> {

    private static final long serialVersionUID = -310932444L;

    public static final QMemberImage memberImage = new QMemberImage("memberImage");

    public final StringPath memberImageName = createString("memberImageName");

    public final NumberPath<Long> memberImageNum = createNumber("memberImageNum", Long.class);

    public final StringPath memberImagePath = createString("memberImagePath");

    public QMemberImage(String variable) {
        super(MemberImage.class, forVariable(variable));
    }

    public QMemberImage(Path<? extends MemberImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberImage(PathMetadata metadata) {
        super(MemberImage.class, metadata);
    }

}

