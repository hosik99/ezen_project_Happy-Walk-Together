package com.ezen.project.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWalkTUser is a Querydsl query type for WalkTUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWalkTUser extends EntityPathBase<WalkTUser> {

    private static final long serialVersionUID = 1985707033L;

    public static final QWalkTUser walkTUser = new QWalkTUser("walkTUser");

    public final StringPath id = createString("id");

    public final NumberPath<Integer> num = createNumber("num", Integer.class);

    public final StringPath pw = createString("pw");

    public QWalkTUser(String variable) {
        super(WalkTUser.class, forVariable(variable));
    }

    public QWalkTUser(Path<? extends WalkTUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWalkTUser(PathMetadata metadata) {
        super(WalkTUser.class, metadata);
    }

}

