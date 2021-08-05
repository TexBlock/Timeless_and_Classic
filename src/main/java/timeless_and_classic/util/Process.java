package timeless_and_classic.util;

@FunctionalInterface
public interface Process<T> {
	T process(T t);
}