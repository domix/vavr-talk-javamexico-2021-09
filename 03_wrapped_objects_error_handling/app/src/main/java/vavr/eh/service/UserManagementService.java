package vavr.eh.service;

import io.micronaut.core.annotation.NonNull;
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
  AddUserResult addUser(final @NonNull User user);

  /**
   * Search in the User catalog a user with the given id
   *
   * @param id The given id
   * @return A non-empty {@link Optional} if found
   */
  @NonNull
  Optional<User> findById(final @NonNull String id);

  @NonNull
  default AddUserResult addUserSuccessful(final @NonNull User saved) {
    return AddUserResult.builder()
        .success(true)
        .user(saved)
        .build();
  }

  @NonNull
  default AddUserResult duplicatedUserResult(final @NonNull User user) {
    return AddUserResult.builder()
        .success(false)
        .user(user)
        .errorMessage("The user with username '%s' already exists.".formatted(user.username()))
        .build();
  }
}
