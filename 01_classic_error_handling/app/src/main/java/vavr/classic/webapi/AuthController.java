package vavr.classic.webapi;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import lombok.RequiredArgsConstructor;
import vavr.classic.service.AuthService;

import java.util.Map;

@Controller("/login")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @Post
  public HttpResponse<?> login(@Body LoginCommand loginCommand) {
    if (authService.login(loginCommand.username(), loginCommand.password())) {
      return HttpResponse.ok(Map.of("success", "true"));
    }

    return HttpResponse.unauthorized();
  }
}
