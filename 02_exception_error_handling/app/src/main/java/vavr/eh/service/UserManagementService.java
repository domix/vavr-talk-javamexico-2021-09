package vavr.eh.service;

import vavr.eh.domain.User;

import java.util.Optional;

public interface UserManagementService {
  User addUser(User user);

  Optional<User> findById(String id);
}
