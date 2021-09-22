package vavr.eh.webapi.domain;

public record AddUserCommand(
    String username,
    String password) {
}
