package nc.apps.dto;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum OrderingBy {
    AUTHOR(1), LANGUAGE(2), TITLE(3), PUBLISHER(4), CATEGORY(5);
    private final int value;

    OrderingBy(int value) {
        this.value = value;
    }

    public static Optional<OrderingBy> valueOf(int value) {
        return Arrays.stream(values())
                .filter(ordBy -> ordBy.value == value)
                .findFirst();
    }
}
