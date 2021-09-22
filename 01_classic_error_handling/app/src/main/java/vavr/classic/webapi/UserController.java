package vavr.classic.webapi;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;
import vavr.classic.service.UserManagementService;

import java.util.Objects;

@Controller("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserManagementService userManagementService;

  @Post
  public HttpResponse<?> addUser(@Body AddUserCommand command) {
    final var saved = userManagementService.addUser(UserWebApiMapper.of(command));
    if (Objects.nonNull(saved.id())) {
      final var location = HttpResponse.uri("/users/%s".formatted(saved.id()));
      return HttpResponse.created(UserWebApiMapper.of(saved), location);
    } else {
      return HttpResponse.badRequest();
    }
  }

  @Get("/{id}")
  public HttpResponse<?> findById(@PathVariable String id) {
    return userManagementService.findById(id)
        .map(user -> HttpResponse.ok(UserWebApiMapper.of(user)))
        .orElseGet(HttpResponse::notFound);
  }
}
