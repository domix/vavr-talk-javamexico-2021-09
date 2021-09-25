package vavr.eh.repository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import vavr.eh.domain.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Contract to perform database operations
 */
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface UserRepository extends CrudRepository<User, UUID> {
  @NonNull
  Optional<User> findByUsernameAndPassword(final @NonNull String username, final @NonNull String password);

  @NonNull
  Optional<User> findByUsername(final @NonNull String username);
}
