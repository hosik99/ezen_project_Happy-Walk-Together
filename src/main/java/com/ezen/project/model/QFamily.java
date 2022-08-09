package com.ezen.project.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFamily is a Querydsl query type for Family
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFamily extends EntityPathBase<Family> {

    private static final long serialVersionUID = -889843647L;

    public static final QFamily family = new QFamily("family");

    public final StringPath delEmail = createString("delEmail");

    public final NumberPath<Long> familyId = createNumber("familyId", Long.class);

    public final StringPath familyPwd = createString("familyPwd");

    public final ListPath<Member, QMember> members = this.<Member, QMember>createList("members", Member.class, QMember.class, PathInits.DIRECT2);

    public final ListPath<Pet, QPet> pets = this.<Pet, QPet>createList("pets", Pet.class, QPet.class, PathInits.DIRECT2);

    public QFamily(String variable) {
        super(Family.class, forVariable(variable));
    }

    public QFamily(Path<? extends Family> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFamily(PathMetadata metadata) {
        super(Family.class, metadata);
    }

}

