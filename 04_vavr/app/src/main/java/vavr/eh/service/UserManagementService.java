package vavr.eh.service;

import io.micronaut.core.annotation.NonNull;
import io.vavr.control.Either;
import vavr.eh.Failure;
import vavr.eh.domain.User;

import java.util.Optional;

/**
 * Contract for User Management operations
 */
public interface UserManagementService {
  /**
   * Adds the given user to the user catalog
   *
   * @param user The given user
   * @return The {@link User} with all the database properties
   */
  @NonNull
  Either<Failure, User> addUser(final @NonNull User user);

  /**
   * Search in the User catalog a user with the given id
   *
   * @param id The given id
   * @return A non-empty {@link Optional} if found
   */
  @NonNull
  Either<Failure, User> getUserById(final @NonNull String id);

  @NonNull
  Either<Failure, User> getUserByUsername(final @NonNull String username);
}
