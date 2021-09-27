package vavr.eh.webapi;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vavr.eh.Failure;
import vavr.eh.domain.User;
import vavr.eh.service.AuthService;
import vavr.eh.webapi.domain.LoginCommand;

import java.util.Map;

@Slf4j
@Controller("/login")
@RequiredArgsConstructor
public class AuthController {
  @NonNull
  private final AuthService authService;

  @NonNull
  @Post
  public HttpResponse<?> login(final @NonNull @Body LoginCommand loginCommand) {
    return authService
        .login(loginCommand.username(), loginCommand.password())
        .fold(this::badCredentials, this::authenticatedUser);
  }

  @NonNull
  private HttpResponse<?> authenticatedUser(final @NonNull User user) {
    final var bodyResponse = Map.of(
        "authenticated", "true",
        "userId", user.idAsString()
    );
    return HttpResponse.ok(bodyResponse);
  }

  @NonNull
  private HttpResponse<?> badCredentials(final @NonNull Failure failure) {
    log.warn("Got a failure while trying to login. Due {}", failure.getReason());
    failure.getCause().peek(throwable -> log.warn(throwable.getMessage(), throwable));
    //obscure by design
    return HttpResponse.unauthorized();
  }
}
