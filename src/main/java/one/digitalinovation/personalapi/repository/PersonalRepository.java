package one.digitalinovation.personalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import one.digitalinovation.personalapi.entity.Person;

public interface PersonalRepository extends JpaRepository<Person, Long> {

}
