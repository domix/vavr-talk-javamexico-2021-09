package vavr.eh.service.impl;

import io.micronaut.core.annotation.NonNull;
import io.vavr.control.Either;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import vavr.eh.Failure;
import vavr.eh.domain.User;
import vavr.eh.repository.UserRepository;
import vavr.eh.service.AuthService;

import java.util.Objects;

import static vavr.eh.service.Failures.givenPasswordDoesNotMatch;
import static vavr.eh.service.Failures.userNotFound;
import static vavr.eh.util.Eithers.ofOption;
import static vavr.eh.util.Eithers.ofOptional;

@Singleton
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {

  @NonNull
  final private UserRepository userRepository;

  @NonNull
  @Override
  public Either<Failure, User> login(
      final @NonNull String username,
      final @NonNull String givenPassword
  ) {
    final var foundUser = ofOptional(
        () -> userRepository.findByUsername(username),
        () -> userNotFound(username)
    );

    return ofOption(
        foundUser.filter(user -> passwordMatches(user, givenPassword)),
        () -> givenPasswordDoesNotMatch(username)
    );
  }

  private boolean passwordMatches(final @NonNull User user, final @NonNull String givenPassword) {
    //put here a better logic to validate the password.
    //Remember, saving plain passwords in databases is a huge mistake.
    return Objects.equals(givenPassword, user.password());
  }

}
