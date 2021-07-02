package us.peaky.counterpack.api;

import java.util.function.Function;

public interface ChatCounter {
  Long getValue();

  Long getChatId();

  void setValue(long value);

  default void setValue(Function<Long, Long> function) {
    setValue(function.apply(getValue()));
  }

  default void increment() {
    setValue(getValue() + 1);
  }

  default void decrement() {
    setValue(Math.max(0, getValue() - 1));
  }
}
