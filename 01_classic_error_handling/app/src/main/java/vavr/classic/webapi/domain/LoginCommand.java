package vavr.classic.webapi.domain;

public record LoginCommand(
    String username,
    String password) {
}
