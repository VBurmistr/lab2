package nc.apps.dao;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.BookDAO;
import nc.apps.dto.BookDBFilter;
import nc.apps.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class BookDAOImpl implements BookDAO {
    private final DataSource dataSource;

    @Autowired
    public BookDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private static final String SQL_DELETE_BOOK = "" +
            "            DELETE FROM\n" +
            "                lab3_book_table\n" +
            "            WHERE\n" +
            "                id = ?";
    public static final String SQL_GET_ALL = "SELECT\n" +
            "                book.id,\n" +
            "                book.title,\n" +
            "                book.author_id,\n" +
            "                author.first_name,\n" +
            "                author.last_name,\n" +
            "                book.language_id,\n" +
            "                lang.language,\n" +
            "                book.publisher_id,\n" +
            "                publisher.publisher_name,\n" +
            "                book.category_id,\n" +
            "                category.category_name,\n" +
            "                bookprequel.id as prequel_id,\n" +
            "                bookprequel.title as prequel_title\n" +
            "            FROM\n" +
            "                lab3_author_table author,\n" +
            "                lab3_category_table category,\n" +
            "                lab3_language_table lang,\n" +
            "                lab3_publisher_table publisher,\n" +
            "                lab3_book_table book\n" +
            "                LEFT OUTER JOIN lab3_book_table bookprequel ON book.prequel_id = bookprequel.id\n" +
            "            where\n" +
            "                book.author_id = author.id\n" +
            "                and book.publisher_id = publisher.id\n" +
            "                and book.category_id = category.id\n" +
            "                and book.language_id = lang.id";

    public static final String SQL_GET_ALL_SIZE = "SELECT\n" +
            "                count(*)\n" +
            "            FROM\n" +
            "                lab3_author_table author,\n" +
            "                lab3_category_table category,\n" +
            "                lab3_language_table lang,\n" +
            "                lab3_publisher_table publisher,\n" +
            "                lab3_book_table book\n" +
            "                LEFT OUTER JOIN lab3_book_table bookprequel ON book.prequel_id = bookprequel.id\n" +
            "            where\n" +
            "                book.author_id = author.id\n" +
            "                and book.publisher_id = publisher.id\n" +
            "                and book.category_id = category.id\n" +
            "                and book.language_id = lang.id";


    public static final String SQL_ADD_NEW = "INSERT INTO\n" +
            "                lab3_book_table (\n" +
            "                    title,\n" +
            "                    author_id,\n" +
            "                    category_id,\n" +
            "                    language_id,\n" +
            "                    publisher_id,\n" +
            "                    prequel_id\n" +
            "                )\n" +
            "            VALUES\n" +
            "                (?, ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_BOOK = "UPDATE\n" +
            "                lab3_book_table\n" +
            "            SET\n" +
            "                title = ?,\n" +
            "                author_id = ?,\n" +
            "                category_id = ?,\n" +
            "                language_id = ?,\n" +
            "                publisher_id = ?,\n" +
            "                prequel_id = ?\n" +
            "            WHERE\n" +
            "                id = ?";

    @Override
    public List<Book> getAll(BookDBFilter bookDBFilter) throws DAOException{
        List<Book> books = new ArrayList<>();
        List<String> params = new ArrayList<>();
        String sql = applyFiltersAndFillParameters(SQL_GET_ALL, bookDBFilter, params,true);
        sql = sql + " LIMIT ? OFFSET ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            int i;
            for (i = 0; i < params.size(); i++) {
                statement.setString(i + 1, params.get(i));
            }
            statement.setInt(++i, bookDBFilter.getLimit());
            statement.setInt(++i, bookDBFilter.getOffset());
            log.info("Query for get all books:" + sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Author author = parseAuthor(resultSet);
                Category category = parseCategory(resultSet);
                Language language = parseLanguage(resultSet);
                Publisher publisher = parsePublisher(resultSet);
                BookBaseModel bookBaseModelPrequel = parseBookBaseModelPrequel(resultSet);
                books.add(new Book(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        author,
                        category,
                        language, publisher, bookBaseModelPrequel));
            }
        } catch (SQLException e) {
            throw new DAOException("Error when trying getting all books by filter:"+ bookDBFilter,e);
        }
        return books;
    }

    @Override
    public Book getById(long id) throws DAOException {
        Book book = new Book();
        String sqlGetById = SQL_GET_ALL + " and book.id = ?";
        log.info("Query for get book by id:" + sqlGetById);
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sqlGetById)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Author author = parseAuthor(resultSet);
                Category category = parseCategory(resultSet);
                Language language = parseLanguage(resultSet);
                Publisher publisher = parsePublisher(resultSet);
                BookBaseModel bookBaseModelPrequel = parseBookBaseModelPrequel(resultSet);
                book = new Book(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        author,
                        category,
                        language, publisher, bookBaseModelPrequel);
            }
        } catch (SQLException e) {
            throw new DAOException("Error getting by id:"+id,e);
        }
        return book;
    }

    private Author parseAuthor(ResultSet resultSet) throws SQLException {
        return new Author(resultSet.getLong("author_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"));
    }

    private Category parseCategory(ResultSet resultSet) throws SQLException {
        return new Category(resultSet.getLong("category_id"),
                resultSet.getString("category_name"));
    }

    private BookBaseModel parseBookBaseModelPrequel(ResultSet resultSet) throws SQLException {
        return new BookBaseModel(resultSet.getLong("prequel_id"),
                resultSet.getString("prequel_title"));
    }

    private Publisher parsePublisher(ResultSet resultSet) throws SQLException {
        return new Publisher(resultSet.getLong("publisher_id"),
                resultSet.getString("publisher_name"));
    }

    private Language parseLanguage(ResultSet resultSet) throws SQLException {
        return new Language(resultSet.getLong("language_id"),
                resultSet.getString("language"));
    }


    public String applyFiltersAndFillParameters(String oldSql, BookDBFilter bookDBFilter, List<String> params, boolean checkOrder){
        StringBuilder sbNewSql = new StringBuilder();
        sbNewSql.append(oldSql);
        if (bookDBFilter.getTitle() != null) {
            sbNewSql.append(" and book.title LIKE ?");
            params.add("%" + bookDBFilter.getTitle() + "%");
        }
        if (bookDBFilter.getAuthorName() != null) {
            sbNewSql.append(" and concat(author.first_name, ' ', author.last_name) LIKE ?");
            params.add("%" + bookDBFilter.getAuthorName() + "%");
        }
        if (bookDBFilter.getCategory() != null) {
            sbNewSql.append(" and category.category_name LIKE ?");
            params.add("%" + bookDBFilter.getCategory() + "%");
        }
        if (bookDBFilter.getPublisher() != null) {
            sbNewSql.append(" and publisher.publisher_name LIKE ?");
            params.add("%" + bookDBFilter.getPublisher() + "%");
        }
        if (bookDBFilter.getLanguage() != null) {
            sbNewSql.append(" and lang.language LIKE ?");
            params.add("%" + bookDBFilter.getLanguage() + "%");
        }
        if (bookDBFilter.getOrderingBy() != null&&checkOrder) {
            switch (bookDBFilter.getOrderingBy()){
                case TITLE:
                    sbNewSql.append(" order by book.title");
                    break;
                case AUTHOR:
                    sbNewSql.append(" order by concat(author.first_name,' ',author.last_name)");
                    break;
                case CATEGORY:
                    sbNewSql.append(" order by category.category_name");
                    break;
                case LANGUAGE:
                    sbNewSql.append(" order by lang.language");
                    break;
                case PUBLISHER:
                    sbNewSql.append(" order by publisher.publisher_name");
                    break;
            }
            if(bookDBFilter.getOrdering()!=null){
                switch (bookDBFilter.getOrdering()) {
                    case ASC:
                        sbNewSql.append(" ASC");
                        break;
                    case DESC:
                        sbNewSql.append(" DESC");
                        break;
                }
            }
        }
        return sbNewSql.toString();
    }

    @Override
    public int allBooksSize(BookDBFilter bookDBFilter) throws DAOException{
        int size = 0;
        List<String> params = new ArrayList<>();
        String sql = applyFiltersAndFillParameters(SQL_GET_ALL_SIZE, bookDBFilter, params,false);
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                statement.setString(i + 1, params.get(i));
            }
            log.info("Query for getting books size by filter:" + sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                size = rs.getInt("count");
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new DAOException("Error fetching books size by filter:"+ bookDBFilter,e);
        }
        return size;
    }

    @Override
    public void save(Book book) throws DAOException{
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_ADD_NEW)) {
            statement.setString(1, book.getTitle());
            statement.setLong(2, book.getAuthor().getId());
            statement.setLong(3, book.getCategory().getId());
            statement.setLong(4, book.getLanguage().getId());
            statement.setLong(5, book.getPublisher().getId());
            statement.setObject(6, book.getPrequel().getId());
            log.info("Query for adding book:" + SQL_ADD_NEW);
            int res = statement.executeUpdate();
            if (res == 0) {
                throw new DAOException("Cant add book for some reason, book:"+book);
            }
        } catch (SQLException e) {
            throw new DAOException("Error on saving book:"+book,e);
        }
    }

    @Override
    public void update(Book book) throws DAOException{
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_BOOK)) {
            statement.setString(1, book.getTitle());
            statement.setLong(2, book.getAuthor().getId());
            statement.setLong(3, book.getCategory().getId());
            statement.setLong(4, book.getLanguage().getId());
            statement.setLong(5, book.getPublisher().getId());
            statement.setObject(6, book.getPrequel().getId());
            statement.setObject(7, book.getId());
            log.info("Query for addinf book:" + SQL_ADD_NEW);
            int res = statement.executeUpdate();
            if (res == 0) {
                throw new DAOException("Cant update book for some reason:"+book);
            }
        } catch (SQLException e) {
            throw new DAOException("Error on updating book:"+book,e);
        }
    }

    @Override
    public void remove(long id) throws DAOException{
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_DELETE_BOOK)) {
            statement.setLong(1, id);
            int res = statement.executeUpdate();
            if (res == 0) {
                throw new DAOException("Cant remove book for some reason, book id:"+id);
            }
        } catch (SQLException e) {
            throw new DAOException("Error on removing book with id:"+id,e);
        }
    }
}
