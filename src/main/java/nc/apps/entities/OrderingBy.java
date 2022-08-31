package nc.apps.entities;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum OrderingBy {
    AUTHOR(1,"author.firstName"),
    LANGUAGE(2,"language.languageName"),
    TITLE(3,"title"),
    PUBLISHER(4,"publisher.publisherName"),
    CATEGORY(5,"category.categoryName");
    private final int value;
    private final String jpaOrderingField;

    OrderingBy(int value,String jpaOrderingField) {
        this.jpaOrderingField = jpaOrderingField;
        this.value = value;
    }

    public static Optional<OrderingBy> valueOf(int value) {
        return Arrays.stream(values())
                .filter(ordBy -> ordBy.value == value)
                .findFirst();
    }
}
