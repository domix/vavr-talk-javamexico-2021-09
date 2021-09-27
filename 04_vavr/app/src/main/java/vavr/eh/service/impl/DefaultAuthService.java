package vavr.eh.service.impl;

import io.micronaut.core.annotation.NonNull;
import io.vavr.control.Either;
import io.vavr.control.Option;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import vavr.eh.Failure;
import vavr.eh.domain.User;
import vavr.eh.service.AuthService;
import vavr.eh.service.UserManagementService;

import java.util.Objects;

import static vavr.eh.service.Failures.givenPasswordDoesNotMatch;
import static vavr.eh.util.Eithers.ofOption;

@Singleton
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {
  final private UserManagementService userManagementService;

  @NonNull
  @Override
  public Either<Failure, User> login(
      final @NonNull String username,
      final @NonNull String givenPassword
  ) {
    return ofOption(
        usersCredentialsAreRight(username, givenPassword),
        () -> givenPasswordDoesNotMatch(username)
    );
  }

  @NonNull
  private Option<Either<Failure, User>> usersCredentialsAreRight(
      final @NonNull String username,
      final @NonNull String givenPassword
  ) {
    return userManagementService.getUserByUsername(username)
        .filter(user -> passwordMatches(user, givenPassword));
  }

  private boolean passwordMatches(
      final @NonNull User user,
      final @NonNull String givenPassword
  ) {
    //put here a better logic to validate the password.
    //Remember, saving plain passwords in databases is a huge mistake.
    return Objects.equals(givenPassword, user.password());
  }

}
