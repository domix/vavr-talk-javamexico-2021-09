package vavr.eh.service;

import io.micronaut.core.annotation.NonNull;
import vavr.eh.Failure;
import vavr.eh.domain.User;

public class Failures {
  @NonNull
  public static Failure duplicatedUserResult(final @NonNull User user) {
    return Failure.builder()
        .reason("The user with username '%s' already exists.".formatted(user.username()))
        .build();
  }

  @NonNull
  public static Failure userNotFound(final @NonNull String username) {
    return Failure.builder()
        .reason("The user with username '%s' does not exist.".formatted(username))
        .build();
  }

  @NonNull
  public static Failure givenPasswordDoesNotMatch(final @NonNull String username) {
    return Failure.builder()
        .reason("The given password does not match for username '%s'".formatted(username))
        .build();
  }
}
