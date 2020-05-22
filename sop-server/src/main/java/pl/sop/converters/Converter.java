package pl.sop.converters;

import java.text.ParseException;

public interface Converter<T, S> {
    S convert(T input);
}
