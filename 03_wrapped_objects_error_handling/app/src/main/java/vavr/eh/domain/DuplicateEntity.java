package vavr.eh.domain;

import io.micronaut.core.annotation.NonNull;
import lombok.Getter;

@Getter
public class DuplicateEntity extends RuntimeException {
  private final String entityName;
  private final String entityId;

  public DuplicateEntity(final @NonNull String entityName, final @NonNull String entityId) {
    super("Duplicated %s with id: '%s'".formatted(entityName, entityId));
    this.entityName = entityName;
    this.entityId = entityId;
  }

  public static DuplicateEntity user(final @NonNull String username) {
    return new DuplicateEntity("User", username);
  }
}
