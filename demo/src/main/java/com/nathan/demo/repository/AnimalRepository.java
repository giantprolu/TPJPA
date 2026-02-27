package com.nathan.demo.repository;

import com.nathan.demo.model.Animal;
import com.nathan.demo.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    // Retourne tous les animaux de la Species fournie en paramètre
    List<Animal> findBySpecies(Species species);

    // Retourne tous les animaux dont la couleur fait partie de la liste fournie
    List<Animal> findByColorIn(List<String> colors);

    //Requête qui renvoie le nombre d’Animaux dont le Sex est égal à la valeur donnée en paramètres
    @Query("SELECT COUNT(a) FROM Animal a WHERE a.sex = :sex")
    long countBySex(String sex);

    //Requête qui renvoie un booléen si l’animal fourni « appartient » à au moins une personne
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Person p JOIN p.animals a WHERE a.name = :name")
    boolean existsPersonHavingAnimalWithName(@Param("name") String name);
}
