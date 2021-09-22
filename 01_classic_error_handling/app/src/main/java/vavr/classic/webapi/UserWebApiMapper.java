package vavr.classic.webapi;

import vavr.classic.domain.User;

public class UserWebApiMapper {
  public static User of(AddUserCommand command) {
    return new User(null, null, command.getUsername(), command.getPassword());
  }

  public static UserDTO of(User user) {
    return new UserDTO(user.id().toString(), user.username());
  }
}
