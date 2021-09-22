package vavr.eh.webapi.domain;

public record LoginCommand(
    String username,
    String password) {
}
