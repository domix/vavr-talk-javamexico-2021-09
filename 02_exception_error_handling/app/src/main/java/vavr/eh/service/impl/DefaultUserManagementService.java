package vavr.eh.service.impl;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vavr.eh.domain.DuplicateEntity;
import vavr.eh.domain.User;
import vavr.eh.repository.UserRepository;
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
  public User addUser(final @NonNull User user) {
    //prevent duplicated users
    if (userRepository.findByUsername(user.username()).isPresent()) {
      throw DuplicateEntity.user(user.username());
    }
    final var saved = userRepository.save(user);
    log.info("Added user with ID: {}", saved.id());
    return saved;
  }

  @NonNull
  @Override
  public Optional<User> findById(final @NonNull String id) {
    final var uuid = UUID.fromString(id);
    return userRepository.findById(uuid);
  }
}
