package com.ezen.project.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWalkTAdmin is a Querydsl query type for WalkTAdmin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWalkTAdmin extends EntityPathBase<WalkTAdmin> {

    private static final long serialVersionUID = 1408466113L;

    public static final QWalkTAdmin walkTAdmin = new QWalkTAdmin("walkTAdmin");

    public final StringPath adminId = createString("adminId");

    public final StringPath adminPw = createString("adminPw");

    public final NumberPath<Integer> num = createNumber("num", Integer.class);

    public QWalkTAdmin(String variable) {
        super(WalkTAdmin.class, forVariable(variable));
    }

    public QWalkTAdmin(Path<? extends WalkTAdmin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWalkTAdmin(PathMetadata metadata) {
        super(WalkTAdmin.class, metadata);
    }

}

