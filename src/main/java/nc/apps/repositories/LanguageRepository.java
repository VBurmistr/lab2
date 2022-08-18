package nc.apps.repositories;

import nc.apps.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
    Optional<Language> findByLanguageName(String languageName);

}
