package com.nathan.demo.repository;

import com.nathan.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    // Retourne les personnes ayant pour nom OU pour prénom les paramètres fournis
    List<Person> findByLastnameOrFirstname(String lastname, String firstname);

    // Retourne les personnes d'un âge supérieur ou égal au paramètre
    List<Person> findByAgeGreaterThanEqual(Integer age);

    //Écrire une méthode qui va chercher les Personnes dont l’âge est entre « age min » et «age max »
    @Query("SELECT p FROM Person p WHERE p.age >= :minAge AND p.age <= :maxAge")
    List<Person> findByAgeBetween(Integer minAge, Integer maxAge);

    @Query("SELECT p FROM Person p JOIN p.animals a WHERE a.id = :animalId")
    List<Person> findByAnimals_Id(Integer animalId);
}
