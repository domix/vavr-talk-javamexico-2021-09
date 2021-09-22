package vavr.classic.webapi;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import lombok.RequiredArgsConstructor;
import vavr.classic.service.AuthService;
import vavr.classic.webapi.domain.LoginCommand;

import java.util.Map;

@Controller("/login")
@RequiredArgsConstructor
public class AuthController {
  @NonNull
  private final AuthService authService;

  @NonNull
  @Post
  public HttpResponse<?> login(final @NonNull @Body LoginCommand loginCommand) {
    if (authService.login(loginCommand.username(), loginCommand.password())) {
      return HttpResponse.ok(Map.of("authenticated", "true"));
    }

    return HttpResponse.unauthorized();
  }
}
