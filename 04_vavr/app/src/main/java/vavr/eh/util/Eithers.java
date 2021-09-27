package vavr.eh.util;


import io.micronaut.core.annotation.NonNull;
import io.vavr.CheckedFunction0;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import vavr.eh.Failure;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Convenient class to create {@link Either} from some Java types and with integration with some classes like {@link Failure}
 */
public class Eithers {
  /**
   * Given a supplier it will convert the returning value to a {@link Either<Throwable, T>}.
   * Useful to prevent write some glue code.
   *
   * @param supplier The given supplier.
   * @param <T>      The expected returning type.
   * @return an instance ready to be used.
   */
  @NonNull
  public static <T> Either<Throwable, T> withThrowable(CheckedFunction0<Optional<T>> supplier) {
    return Try.of(supplier::apply)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .toEither();
  }

  @NonNull
  public static <T> Either<Failure, T> ofOptional(
      final @NonNull CheckedFunction0<Optional<T>> supplier,
      final @NonNull Supplier<Failure> leftSupplier
  ) {
    return Try.of(supplier::apply)
        .toEither()
        .mapLeft(Failure::of)
        .filterOrElse(Optional::isPresent, t -> leftSupplier.get())
        .map(Optional::get);
  }

  @NonNull
  public static <R> Either<Failure, R> ofOption(
      final @NonNull Option<Either<Failure, R>> option,
      final @NonNull Supplier<Failure> leftSupplier
  ) {
    return option.toEither(leftSupplier.get())
        .flatMap(result -> result);
  }
}