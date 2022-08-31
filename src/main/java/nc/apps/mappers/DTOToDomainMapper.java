package nc.apps.mappers;

import nc.apps.dto.BookIDsDTO;
import nc.apps.dto.tabledtos.AuthorDTO;
import nc.apps.dto.tabledtos.CategoryDTO;
import nc.apps.dto.tabledtos.LanguageDTO;
import nc.apps.dto.tabledtos.PublisherDTO;
import nc.apps.entities.domain.*;

public class DTOToDomainMapper {
    public static Book mapBooks(BookIDsDTO bookIDsDTO) {
        Book book = Book.builder()
                .title(bookIDsDTO.getTitle())
                .language(Language
                        .builder()
                        .id(Integer.parseInt(bookIDsDTO.getLanguageId()))
                        .build())
                .author(Author
                        .builder()
                        .id(Integer.parseInt(bookIDsDTO.getAuthorId()))
                        .build())
                .category(Category
                        .builder()
                        .id(Integer.parseInt(bookIDsDTO.getCategoryId()))
                        .build())
                .publisher(Publisher
                        .builder()
                        .id(Integer.parseInt(bookIDsDTO.getPublisherId()))
                        .build())
                .prequel(null)
                .build();
        if (!(bookIDsDTO.getPrequelId().equals("0") || bookIDsDTO.getPrequelId().isEmpty())) {
            book.setPrequel(Book.builder()
                    .id(Integer.parseInt(bookIDsDTO.getPrequelId()))
                    .build());
        }
        return book;
    }

    public static Book mapBook(BookIDsDTO bookIDsDTO) {
        Book book = Book.builder()
                .title(bookIDsDTO.getTitle())
                .language(Language
                        .builder()
                        .id(Integer.parseInt(bookIDsDTO.getLanguageId()))
                        .build())
                .author(Author
                        .builder()
                        .id(Integer.parseInt(bookIDsDTO.getAuthorId()))
                        .build())
                .category(Category
                        .builder()
                        .id(Integer.parseInt(bookIDsDTO.getCategoryId()))
                        .build())
                .publisher(Publisher
                        .builder()
                        .id(Integer.parseInt(bookIDsDTO.getPublisherId()))
                        .build())
                .prequel(null)
                .build();
        if (!(bookIDsDTO.getPrequelId().equals("0") || bookIDsDTO.getPrequelId().isEmpty())) {
            book.setPrequel(Book.builder()
                    .id(Integer.parseInt(bookIDsDTO.getPrequelId()))
                    .build());
        }
        return book;
    }

    public static Author mapAuthor(AuthorDTO author) {
        return Author.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }
    public static Category mapCategory(CategoryDTO category) {
        return Category.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }
    public static Language mapLanguage(LanguageDTO language) {
        return Language.builder()
                .id(language.getId())
                .languageName(language.getLanguageName())
                .build();
    }
    public static Publisher mapPublisher(PublisherDTO publisher) {
        return Publisher.builder()
                .id(publisher.getId())
                .publisherName(publisher.getPublisherName())
                .build();
    }

}
