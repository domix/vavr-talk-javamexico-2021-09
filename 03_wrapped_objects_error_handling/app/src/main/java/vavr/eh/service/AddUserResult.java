package vavr.eh.service;

import lombok.Builder;
import lombok.Getter;
import vavr.eh.domain.User;

import java.util.Optional;

/**
 * Represents the dara
 */
@Builder
@Getter
public class AddUserResult {
  @Builder.Default
  private final boolean success = false;
  private final User user;
  private final String errorMessage;
  private final Optional<Throwable> errorCause;
}
