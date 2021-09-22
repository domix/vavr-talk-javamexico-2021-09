package vavr.classic.webapi;

import io.micronaut.core.annotation.NonNull;
import vavr.classic.domain.User;
import vavr.classic.webapi.domain.AddUserCommand;
import vavr.classic.webapi.domain.UserDTO;

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
