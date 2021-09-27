package vavr.eh.service.impl;

import io.micronaut.core.annotation.NonNull;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vavr.eh.Failure;
import vavr.eh.domain.User;
import vavr.eh.repository.UserRepository;
import vavr.eh.service.UserManagementService;

import java.util.UUID;

import static vavr.eh.service.Failures.duplicatedUserResult;
import static vavr.eh.service.Failures.userNotFound;
import static vavr.eh.util.Eithers.ofOptional;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class DefaultUserManagementService implements UserManagementService {
  @NonNull
  private final UserRepository userRepository;

  @NonNull
  @Override
  public Either<Failure, User> addUser(final @NonNull User user) {
    return userRepository
        .findByUsername(user.username())
        .map(this::handleExistingUser)
        .orElseGet(() -> internalAddUser(user));
  }

  @NonNull
  @Override
  public Either<Failure, User> findById(final @NonNull String id) {
    final var uuid = UUID.fromString(id);

    return ofOptional(
        () -> userRepository.findById(uuid),
        () -> userNotFound(id)
    );
  }

  @NonNull
  private Either<Failure, User> internalAddUser(final @NonNull User user) {
    log.info("Trying to add user {}", user.username());

    return Try.of(() -> userRepository.save(user))
        .toEither()
        .peek(saved -> log.info("Added user with ID: {}", saved.idAsString()))
        .peekLeft(throwable -> log.warn("Error while trying to save user '{}' due: {}", user.username(), throwable.getMessage(), throwable))
        .mapLeft(Failure::of);
  }

  @NonNull
  private Either<Failure, User> handleExistingUser(final @NonNull User existingUser) {
    log.warn("The username '{}' already exists.", existingUser.username());
    return Either.left(duplicatedUserResult(existingUser));
  }
}
