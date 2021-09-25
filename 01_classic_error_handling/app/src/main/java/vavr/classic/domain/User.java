package vavr.classic.domain;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import java.util.Date;
import java.util.UUID;

/**
 * The User in our system.
 * <p>
 * The password is being stored as plain text, which is not right,
 * just to simplify the development and educational purposes.
 * Don't use this approach.
 */
@MappedEntity("people")
public record User(
    @Nullable @Id @AutoPopulated UUID id,
    @Nullable @DateCreated Date dateCreated,
    @NonNull String username,
    @NonNull String password) {
}
