package vavr.eh.webapi;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vavr.eh.Failure;
import vavr.eh.domain.User;
import vavr.eh.service.UserManagementService;
import vavr.eh.webapi.domain.AddUserCommand;
import vavr.eh.webapi.domain.UserDTO;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller(UserController.URI)
public class UserController {
  public static final String URI = "/users";
  @NonNull
  private final UserManagementService userManagementService;

  @NonNull
  @Post
  public HttpResponse<?> addUser(final @NonNull @Body AddUserCommand command) {
    return userManagementService.addUser(UserWebApiMapper.of(command))
        .fold(
            this::failureWhileSaving,
            this::savedUser
        );
  }

  @NonNull
  @Get("/{id}")
  public HttpResponse<?> findById(final @NonNull @PathVariable String id) {
    return userManagementService.getUserById(id)
        .fold(
            this::userQueryFailure,
            this::renderUser
        );
  }

  @NonNull
  private MutableHttpResponse<Map<String, String>> userQueryFailure(Failure failure) {
    log.warn("Failure found: {}", failure.getReason());
    final var body = Map.of(
        "message", failure.getReason()
    );
    return HttpResponse.notFound(body);
  }

  @NonNull
  private HttpResponse<UserDTO> renderUser(User user) {
    return HttpResponse.ok(UserWebApiMapper.of(user));
  }

  @NonNull
  private HttpResponse<UserDTO> savedUser(User saved) {
    final var location = HttpResponse.uri("%s/%s".formatted(URI, saved.idAsString()));
    return HttpResponse.created(UserWebApiMapper.of(saved), location);
  }

  @NonNull
  private HttpResponse<Map<String, String>> failureWhileSaving(Failure failure) {
    final var body = Map.of(
        "message", failure.getReason()
    );
    return HttpResponse.badRequest(body);
  }
}
