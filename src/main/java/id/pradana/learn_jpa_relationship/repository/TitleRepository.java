package id.pradana.learn_jpa_relationship.repository;

import id.pradana.learn_jpa_relationship.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, Long> {
}
