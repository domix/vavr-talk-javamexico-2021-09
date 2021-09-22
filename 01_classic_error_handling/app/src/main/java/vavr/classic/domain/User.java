package vavr.classic.domain;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import java.util.Date;
import java.util.UUID;

@MappedEntity("people")
public record User(
    @Id @AutoPopulated @Nullable UUID id,
    @DateCreated @Nullable Date dateCreated,
    String username,
    String password) {
}
