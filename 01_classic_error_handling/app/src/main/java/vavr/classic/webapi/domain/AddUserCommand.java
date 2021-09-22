package vavr.classic.webapi.domain;

import io.micronaut.core.annotation.NonNull;

/**
 * Command class to add users to the system
 */
public record AddUserCommand(
    @NonNull String username,
    @NonNull String password) {
}
