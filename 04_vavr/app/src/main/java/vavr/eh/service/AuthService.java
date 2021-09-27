package vavr.eh.service;

import io.micronaut.core.annotation.NonNull;
import io.vavr.control.Either;
import vavr.eh.Failure;
import vavr.eh.domain.User;

/**
 * Contract to perform Authentication operations
 */
public interface AuthService {
  /**
   * Given a user and a password,
   * it will verify if we have a user that matches the given parameters.
   *
   * @param username      The given username
   * @param givenPassword The given password
   * @return {@code true} if parameters match
   */
  @NonNull
  Either<Failure, User> login(
      final @NonNull String username,
      final @NonNull String givenPassword
  );
}
