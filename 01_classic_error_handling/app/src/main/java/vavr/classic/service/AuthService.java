package vavr.classic.service;

import io.micronaut.core.annotation.NonNull;

/**
 * Contract to perform Authentication operations
 */
public interface AuthService {
  /**
   * Given a user and a password, it wil verify if we have a user that matches the given parameters.
   *
   * @param username The given username
   * @param password The given password
   * @return {@code true} if parameters match
   */
  boolean login(@NonNull String username, @NonNull String password);
}
