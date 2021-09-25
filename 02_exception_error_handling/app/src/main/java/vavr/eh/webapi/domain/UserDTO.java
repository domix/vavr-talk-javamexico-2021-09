package vavr.eh.webapi.domain;

import io.micronaut.core.annotation.NonNull;
import vavr.eh.domain.User;

/**
 * DTO to transfers public {@link User} info
 */
public record UserDTO(
    @NonNull String id,
    @NonNull String username) {
}
