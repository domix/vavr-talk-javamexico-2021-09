package vavr.eh.service.impl;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import vavr.eh.repository.UserRepository;
import vavr.eh.service.AuthService;

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
