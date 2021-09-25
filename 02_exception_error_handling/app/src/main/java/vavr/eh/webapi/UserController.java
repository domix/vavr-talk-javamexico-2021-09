package vavr.eh.webapi;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;
import vavr.eh.service.UserManagementService;
import vavr.eh.webapi.domain.AddUserCommand;

import java.util.Objects;

@Controller(UserController.URI)
@RequiredArgsConstructor
public class UserController {
  public static final String URI = "/users";
  @NonNull
  private final UserManagementService userManagementService;

  @NonNull
  @Post
  public HttpResponse<?> addUser(final @NonNull @Body AddUserCommand command) {
    final var saved = userManagementService.addUser(UserWebApiMapper.of(command));
    if (Objects.nonNull(saved.id())) {
      final var location = HttpResponse.uri("%s/%s".formatted(URI, saved.id()));
      return HttpResponse.created(UserWebApiMapper.of(saved), location);
    } else {
      return HttpResponse.badRequest();
    }
  }

  @NonNull
  @Get("/{id}")
  public HttpResponse<?> findById(final @NonNull @PathVariable String id) {
    return userManagementService.findById(id)
        .map(user -> HttpResponse.ok(UserWebApiMapper.of(user)))
        .orElseGet(HttpResponse::notFound);
  }
}
