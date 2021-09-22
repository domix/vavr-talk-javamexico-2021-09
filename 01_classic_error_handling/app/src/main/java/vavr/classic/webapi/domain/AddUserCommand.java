package vavr.classic.webapi.domain;

public record AddUserCommand(
    String username,
    String password) {
}
