package nc.apps.services.jpaservices;

import lombok.extern.slf4j.Slf4j;
import nc.apps.dao.exception.DAOException;
import nc.apps.dao.interfaces.BookBaseModelDAO;
import nc.apps.dto.tabledtos.BookBaseModelDTO;
import nc.apps.entities.Book;
import nc.apps.entities.BookBaseModel;
import nc.apps.mappers.DomainToDTOMapper;
import nc.apps.repositories.BookBaseModelRepository;
import nc.apps.repositories.BookRepository;
import nc.apps.services.exceptions.ServiceException;
import nc.apps.services.interfaces.BookBaseModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Primary
public class BookBaseModelServiceJPAImpl implements BookBaseModelService {
    private final BookRepository bookRepository;

    @Autowired
    public BookBaseModelServiceJPAImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public List<BookBaseModelDTO> getAll() throws ServiceException {
        try {
              List<BookBaseModel> books =  bookRepository.findAll()
                    .stream()
                    .map(b->new BookBaseModel(b.getId(),b.getTitle()))
                    .collect(Collectors.toList());
              return DomainToDTOMapper.mapBaseModels(books);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }
}
