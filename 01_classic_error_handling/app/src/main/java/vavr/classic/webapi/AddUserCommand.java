package vavr.classic.webapi;

public record AddUserCommand(
    String username,
    String password) {
}
