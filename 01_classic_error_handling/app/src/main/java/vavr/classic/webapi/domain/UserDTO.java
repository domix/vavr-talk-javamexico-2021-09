package vavr.classic.webapi.domain;

import io.micronaut.core.annotation.NonNull;
import vavr.classic.domain.User;

/**
 * DTO to transfers public {@link User} info
 */
public record UserDTO(
    @NonNull String id,
    @NonNull String username) {
}
