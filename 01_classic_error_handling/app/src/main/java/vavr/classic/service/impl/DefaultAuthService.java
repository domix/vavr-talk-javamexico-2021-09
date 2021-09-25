package vavr.classic.service.impl;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import vavr.classic.repository.UserRepository;
import vavr.classic.service.AuthService;

@Singleton
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {

  @NonNull
  final private UserRepository userRepository;

  @Override
  public boolean login(
      final @NonNull String username,
      final @NonNull String password
  ) {
    return userRepository
        .findByUsernameAndPassword(username, password)
        .isPresent();
  }
}
