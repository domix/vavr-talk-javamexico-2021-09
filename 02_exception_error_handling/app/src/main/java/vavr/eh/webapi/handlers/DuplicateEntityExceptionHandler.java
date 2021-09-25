package vavr.eh.webapi.handlers;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import vavr.eh.domain.DuplicateEntity;

import java.util.Map;

@Produces
@Singleton
@Requires(classes = {DuplicateEntity.class, ExceptionHandler.class})
public class DuplicateEntityExceptionHandler implements ExceptionHandler<DuplicateEntity, HttpResponse<?>> {

  @Override
  public HttpResponse<?> handle(HttpRequest request, DuplicateEntity exception) {
    final var body = Map.of(
        "message", exception.getMessage(),
        "entity", exception.getEntityName(),
        "duplicatedId", exception.getEntityId()
    );

    return HttpResponse.badRequest(body);
  }
}
