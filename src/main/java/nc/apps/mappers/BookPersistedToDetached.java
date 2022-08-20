package nc.apps.mappers;

import nc.apps.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public class BookPersistedToDetached {
    public static List<Book> map(List<Book> persisted) {
        System.out.println("Mapping");
        return persisted.stream().map(b -> {
            Book book = Book.builder()
                    .id(b.getId())
                    .title(b.getTitle())
                    .language(Language
                            .builder()
                            .languageName(b.getLanguage().getLanguageName())
                            .id(b.getLanguage().getId())
                            .build())
                    .author(Author
                            .builder()
                            .id(b.getAuthor().getId())
                            .lastName(b.getAuthor().getLastName())
                            .firstName(b.getAuthor().getFirstName())
                            .build())
                    .category(Category
                            .builder()
                            .categoryName(b.getCategory().getCategoryName())
                            .id(b.getCategory().getId())
                            .build())
                    .publisher(Publisher
                            .builder()
                            .publisherName(b.getPublisher().getPublisherName())
                            .id(b.getPublisher().getId())
                            .build())
                    .build();
            if (b.getPrequel() != null) {
                book.setPrequel(Book.builder()
                        .id(b.getPrequel().getId())
                        .title(b.getPrequel().getTitle())
                        .build());
            }
            return book;
        }).collect(Collectors.toList());
    }
}
