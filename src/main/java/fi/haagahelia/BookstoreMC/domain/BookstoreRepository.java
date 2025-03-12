package fi.haagahelia.BookstoreMC.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface BookstoreRepository extends CrudRepository<Book, Long> {
    List<Book> findAll();

    List<Book> findByTitle(@Param("title") String title);

    List<Book> findByAuthor(String author);

}
