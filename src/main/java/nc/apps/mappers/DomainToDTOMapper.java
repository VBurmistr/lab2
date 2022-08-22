package nc.apps.mappers;

import nc.apps.dto.tabledtos.*;
import nc.apps.entities.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class DomainToDTOMapper {
    public static List<BookDTO> mapBooks(List<Book> books) {
        return books.stream().map(b -> {
            BookDTO bookDTOs = BookDTO.builder()
                    .id(b.getId())
                    .title(b.getTitle())
                    .language(LanguageDTO.builder()
                            .languageName(b.getLanguage().getLanguageName())
                            .id(b.getLanguage().getId())
                            .build())
                    .author(AuthorDTO.builder()
                            .firstName(b.getAuthor().getFirstName())
                            .lastName(b.getAuthor().getLastName())
                            .id(b.getAuthor().getId())
                            .build())
                    .category(CategoryDTO.builder()
                            .id(b.getCategory().getId())
                            .categoryName(b.getCategory().getCategoryName())
                            .build())
                    .publisher(PublisherDTO.builder()
                            .id(b.getPublisher().getId())
                            .publisherName(b.getPublisher().getPublisherName())
                            .build())
                    .build();
            if (b.getPrequel() != null) {
                bookDTOs.setPrequel(BookBaseModelDTO.builder()
                        .id(b.getPrequel().getId())
                        .title(b.getPrequel().getTitle())
                        .build());
            }
            return bookDTOs;
        }).collect(Collectors.toList());
    }

    public static List<BookDTO> mapBookPage(Page<Book> books) {
        return books.stream().map(b -> {
            BookDTO bookDTOs = BookDTO.builder()
                    .id(b.getId())
                    .title(b.getTitle())
                    .language(LanguageDTO.builder()
                            .languageName(b.getLanguage().getLanguageName())
                            .id(b.getLanguage().getId())
                            .build())
                    .author(AuthorDTO.builder()
                            .firstName(b.getAuthor().getFirstName())
                            .lastName(b.getAuthor().getLastName())
                            .id(b.getAuthor().getId())
                            .build())
                    .category(CategoryDTO.builder()
                            .id(b.getCategory().getId())
                            .categoryName(b.getCategory().getCategoryName())
                            .build())
                    .publisher(PublisherDTO.builder()
                            .id(b.getPublisher().getId())
                            .publisherName(b.getPublisher().getPublisherName())
                            .build())
                    .build();
            if (b.getPrequel() != null) {
                bookDTOs.setPrequel(BookBaseModelDTO.builder()
                        .id(b.getPrequel().getId())
                        .title(b.getPrequel().getTitle())
                        .build());
            }
            return bookDTOs;
        }).collect(Collectors.toList());
    }

    public static BookDTO mapBook(Book book) {
        BookDTO bookDTO = BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .language(LanguageDTO.builder()
                        .languageName(book.getLanguage().getLanguageName())
                        .id(book.getLanguage().getId())
                        .build())
                .author(AuthorDTO.builder()
                        .firstName(book.getAuthor().getFirstName())
                        .lastName(book.getAuthor().getLastName())
                        .id(book.getAuthor().getId())
                        .build())
                .category(CategoryDTO.builder()
                        .id(book.getCategory().getId())
                        .categoryName(book.getCategory().getCategoryName())
                        .build())
                .publisher(PublisherDTO.builder()
                        .id(book.getPublisher().getId())
                        .publisherName(book.getPublisher().getPublisherName())
                        .build())
                .build();
        if (book.getPrequel() != null) {
            bookDTO.setPrequel(BookBaseModelDTO.builder()
                    .id(book.getPrequel().getId())
                    .title(book.getPrequel().getTitle())
                    .build());
        }
        return bookDTO;
    }

    public static List<BookBaseModelDTO> mapBaseModels(List<? extends BookBaseModel> books) {
        return books
                .stream()
                .map(b -> new BookBaseModelDTO(b.getId(), b.getTitle()))
                .collect(Collectors.toList());
    }

    public static List<AuthorDTO> mapAuthors(List<Author> authors) {
        return authors
                .stream()
                .map(a -> new AuthorDTO(a.getId(), a.getFirstName(), a.getLastName()))
                .collect(Collectors.toList());
    }

    public static List<CategoryDTO> mapCategories(List<Category> categories) {
        return categories
                .stream()
                .map(c -> new CategoryDTO(c.getId(), c.getCategoryName()))
                .collect(Collectors.toList());
    }

    public static List<LanguageDTO> mapLanguages(List<Language> languages) {
        return languages
                .stream()
                .map(l -> new LanguageDTO(l.getId(), l.getLanguageName()))
                .collect(Collectors.toList());
    }

    public static List<PublisherDTO> mapPublishers(List<Publisher> publishers) {
        return publishers
                .stream()
                .map(a -> new PublisherDTO(a.getId(), a.getPublisherName()))
                .collect(Collectors.toList());
    }
}
