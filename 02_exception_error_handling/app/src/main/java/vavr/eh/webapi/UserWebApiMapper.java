package vavr.classic.webapi;

import vavr.eh.domain.User;
import vavr.eh.webapi.domain.AddUserCommand;
import vavr.eh.webapi.domain.UserDTO;

public class UserWebApiMapper {
  public static User of(AddUserCommand command) {
    return new User(null, null, command.username(), command.password());
  }

  public static UserDTO of(User user) {
    return new UserDTO(user.id().toString(), user.username());
  }
}
