package nc.apps.mappers;

import nc.apps.dto.BookIDsDTO;
import nc.apps.entities.*;

public class BookIdsDTOToBookDomain {
    public static Book map(BookIDsDTO bookIDsDTO){
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
        if(!bookIDsDTO.getPrequelId().equals("0")){
            book.setPrequel(Book.builder()
                            .id(Integer.parseInt(bookIDsDTO.getPrequelId()))
                            .build());
        }
        return book;
    }
}
