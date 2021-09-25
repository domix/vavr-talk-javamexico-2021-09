package vavr.eh.webapi.handlers;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.transaction.exceptions.TransactionException;
import jakarta.inject.Singleton;

import java.util.Map;

@Produces
@Singleton
@Requires(classes = {TransactionException.class, ExceptionHandler.class})
public class TransactionExceptionHandler implements ExceptionHandler<TransactionException, HttpResponse<?>> {
  @Override
  public HttpResponse<?> handle(HttpRequest request, TransactionException exception) {
    final var body = Map.of(
        "message", exception.getMessage()
    );

    return HttpResponse.serverError(body);
  }
}
