package vavr.classic.service;

import vavr.classic.domain.User;

import java.util.Optional;

public interface UserManagementService {
  User addUser(User user);

  Optional<User> findById(String id);
}
