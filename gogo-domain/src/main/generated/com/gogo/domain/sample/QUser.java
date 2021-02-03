package com.gogo.domain.sample;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<Sample> {

    private static final long serialVersionUID = -678569675L;

    public static final QUser user = new QUser("user");

    public final com.gogo.domain.QBaseTimeEntity _super = new com.gogo.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDateTime = _super.createdDateTime;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDateTime = _super.lastModifiedDateTime;

    public final StringPath name = createString("name");

    public final StringPath profileUrl = createString("profileUrl");

    public QUser(String variable) {
        super(Sample.class, forVariable(variable));
    }

    public QUser(Path<? extends Sample> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(Sample.class, metadata);
    }

}

