package com.ezen.project.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPetImage is a Querydsl query type for PetImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPetImage extends EntityPathBase<PetImage> {

    private static final long serialVersionUID = -767109863L;

    public static final QPetImage petImage = new QPetImage("petImage");

    public final StringPath petImageName = createString("petImageName");

    public final NumberPath<Long> petImageNum = createNumber("petImageNum", Long.class);

    public final StringPath petImagePath = createString("petImagePath");

    public QPetImage(String variable) {
        super(PetImage.class, forVariable(variable));
    }

    public QPetImage(Path<? extends PetImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPetImage(PathMetadata metadata) {
        super(PetImage.class, metadata);
    }

}

