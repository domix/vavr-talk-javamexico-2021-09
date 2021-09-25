package vavr.eh.service.impl;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vavr.eh.domain.User;
import vavr.eh.repository.UserRepository;
import vavr.eh.service.AddUserResult;
import vavr.eh.service.UserManagementService;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class DefaultUserManagementService implements UserManagementService {
  @NonNull
  private final UserRepository userRepository;

  @NonNull
  @Override
  public AddUserResult addUser(final @NonNull User user) {
    return userRepository
        .findByUsername(user.username())
        .map(this::handleExistingUser)
        .orElseGet(() -> internalAddUser(user));
  }

  @NonNull
  @Override
  public Optional<User> findById(final @NonNull String id) {
    final var uuid = UUID.fromString(id);
    return userRepository.findById(uuid);
  }

  @NonNull
  private AddUserResult internalAddUser(final @NonNull User user) {
    log.info("Trying to add user {}", user.username());
    final var saved = userRepository.save(user);
    log.info("Added user with ID: {}", saved.id());

    return addUserSuccessful(saved);
  }

  @NonNull
  private AddUserResult handleExistingUser(final @NonNull User existingUser) {
    log.warn("The username '{}' already exists.", existingUser.username());
    return duplicatedUserResult(existingUser);
  }
}
