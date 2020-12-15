package pl.sop.converters;

public interface Converter<T, S> {
    S convert(T input);
}
