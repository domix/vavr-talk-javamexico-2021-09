package vavr.classic.webapi.domain;

import io.micronaut.core.annotation.NonNull;

/**
 * Command class to perform login operation
 */
public record LoginCommand(
    @NonNull String username,
    @NonNull String password) {
}
