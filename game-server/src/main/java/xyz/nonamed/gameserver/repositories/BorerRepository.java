package xyz.nonamed.gameserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.nonamed.dto.Borer;

import java.util.List;

@Repository
public interface BorerRepository extends JpaRepository<Borer, Long> {

    List<Borer> readAllBySessionCodeEqualsIgnoreCase(String sessionCode);

}
