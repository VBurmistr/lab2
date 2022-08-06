package nc.apps.dao;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.LanguageDAO;
import nc.apps.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Repository
public class LanguageDAOImpl implements LanguageDAO {

    private final DataSource dataSource;
    public static final String SQL_GET_ALL = "SELECT * FROM LAB3_LANGUAGE_TABLE";
    public static final String SQL_ADD_NEW = "INSERT INTO LAB3_LANGUAGE_TABLE (language) VALUES (?)";

    @Autowired
    public LanguageDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Language> getAll() throws DAOException {
        List<Language> languages = new ArrayList<>();
        log.info("Get all languages query:"+SQL_GET_ALL);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_GET_ALL)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                languages.add(new Language(resultSet.getLong("id"),
                        resultSet.getString("language")));
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getting language's.",e);
        }
        return languages;
    }


    @Override
    public void save(Language language)  throws DAOException {
        log.info("Add new language query:"+SQL_GET_ALL);
        try(Connection con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement(SQL_ADD_NEW)){
            statement.setString(1,language.getLanguageName());
            int res = statement.executeUpdate();
            if (res == 0) {
                throw new DAOException("Cant add language for some reason, language:"+language);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while saving language:"+language,e);
        }
    }
}
