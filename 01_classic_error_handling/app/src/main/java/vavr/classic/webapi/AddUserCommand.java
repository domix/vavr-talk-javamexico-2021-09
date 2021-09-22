package vavr.classic.webapi;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddUserCommand {
  private String username;
  private String password;
}
