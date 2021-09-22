package vavr.classic.service.impl;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vavr.classic.domain.User;
import vavr.classic.repository.UserRepository;
import vavr.classic.service.UserManagementService;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class DefaultUserManagementService implements UserManagementService {
  private final UserRepository userRepository;

  @Override
  public User addUser(User user) {
    final var saved = userRepository.save(user);
    log.info("Usuario guardado en: {}, con ID: {}", saved.dateCreated(), saved.id());
    return saved;
  }

  @Override
  public Optional<User> findById(String id) {
    final var uuid = UUID.fromString(id);
    return userRepository.findById(uuid);
  }
}
