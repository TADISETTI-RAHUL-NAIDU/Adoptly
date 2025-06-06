package klu.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import klu.model.Pets;

@Repository
public interface PetsRepository extends JpaRepository<Pets, Long> {

}
