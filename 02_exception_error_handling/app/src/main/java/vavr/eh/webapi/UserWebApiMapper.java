package vavr.eh.webapi;

import io.micronaut.core.annotation.NonNull;
import vavr.eh.domain.User;
import vavr.eh.webapi.domain.AddUserCommand;
import vavr.eh.webapi.domain.UserDTO;

import java.util.Objects;

public class UserWebApiMapper {
  @NonNull
  public static User of(final @NonNull AddUserCommand command) {
    return new User(null, null, command.username(), command.password());
  }

  @NonNull
  public static UserDTO of(final @NonNull User user) {
    return new UserDTO(Objects.requireNonNull(user.id()).toString(), user.username());
  }
}
