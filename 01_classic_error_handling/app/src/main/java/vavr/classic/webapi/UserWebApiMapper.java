package vavr.classic.webapi;

import vavr.classic.domain.User;
import vavr.classic.webapi.domain.AddUserCommand;
import vavr.classic.webapi.domain.UserDTO;

public class UserWebApiMapper {
  public static User of(AddUserCommand command) {
    return new User(null, null, command.username(), command.password());
  }

  public static UserDTO of(User user) {
    return new UserDTO(user.id().toString(), user.username());
  }
}
