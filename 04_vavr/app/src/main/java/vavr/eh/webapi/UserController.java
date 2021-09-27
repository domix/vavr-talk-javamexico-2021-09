package vavr.eh.webapi;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;
import vavr.eh.service.UserManagementService;
import vavr.eh.webapi.domain.AddUserCommand;

@Controller(UserController.URI)
@RequiredArgsConstructor
public class UserController {
  public static final String URI = "/users";
  @NonNull
  private final UserManagementService userManagementService;

  @NonNull
  @Post
  public HttpResponse<?> addUser(final @NonNull @Body AddUserCommand command) {
    return userManagementService.addUser(UserWebApiMapper.of(command))
        .fold(
            failure -> HttpResponse.badRequest(),
            saved -> {
              final var location = HttpResponse.uri("%s/%s".formatted(URI, saved.idAsString()));
              return HttpResponse.created(UserWebApiMapper.of(saved), location);
            });
  }

  @NonNull
  @Get("/{id}")
  public HttpResponse<?> findById(final @NonNull @PathVariable String id) {
    return userManagementService.findById(id)
        .map(user -> HttpResponse.ok(UserWebApiMapper.of(user)))
        .orElseGet(HttpResponse::notFound);
  }
}
