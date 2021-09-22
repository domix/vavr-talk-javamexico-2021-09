package vavr.classic.service.impl;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import vavr.classic.repository.UserRepository;
import vavr.classic.service.AuthService;

@Singleton
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {
  final private UserRepository userRepository;

  @Override
  public boolean login(String username, String password) {
    return userRepository
        .findByUsernameAndPassword(username, password)
        .isPresent();
  }
}
