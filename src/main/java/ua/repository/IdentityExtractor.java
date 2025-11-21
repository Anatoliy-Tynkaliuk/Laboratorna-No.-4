package ua.repository;

@FunctionalInterface
public interface IdentityExtractor<T> {
    String extract(T obj);
}
