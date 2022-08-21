package nc.apps.springdataspecifications;

import nc.apps.entities.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {
    public static Specification<Book> hasTitle(String title) {
        return (book, cq, cb) -> cb.like(book.get("title"), title);
    }
    public static Specification<Book> hasAuthorFirstName(String firstName) {
        return (book, cq, cb) -> cb.like(book.get("author").get("firstName"), firstName);
    }
    public static Specification<Book> hasAuthorLastName(String lastName) {
        return (book, cq, cb) -> cb.like(book.get("author").get("lastName"), lastName);
    }
    public static Specification<Book> hasCategory(String category) {
        return (book, cq, cb) -> cb.like(book.get("category").get("categoryName"), category);
    }
    public static Specification<Book> hasLanguage(String lang) {
        return (book, cq, cb) -> cb.like(book.get("language").get("languageName"), lang);
    }
    public static Specification<Book> hasPublisher(String publisher) {
        return (book, cq, cb) -> cb.like(book.get("publisher").get("publisherName"), publisher);
    }
}
