package xyz.nonamed.gameserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.nonamed.dto.Hero;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

    Hero readByNameEqualsIgnoreCaseAndSessionCodeEqualsIgnoreCase(String name, String sessionCode);

    List<Hero> readAllBySessionCodeEqualsIgnoreCase(String sessionCode);

}
