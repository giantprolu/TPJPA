package com.nathan.demo.repository;

import com.nathan.demo.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpeciesRepository extends JpaRepository<Species, Integer> {

    // Retourne la première Species dont le nom commun est égal au paramètre
    Species findFirstByCommonName(String commonName);

    // Retourne les Species dont le nom latin contient le paramètre, en ignorant la casse
    List<Species> findByLatinNameContainingIgnoreCase(String latinName);

    // Retourne les Species dont par nom commun et ascendant
    @Query("SELECT s FROM Species s ORDER BY s.commonName ASC")
    List<Species> findAllOrderByCommonNameAsc();

    // Écrire une méthode qui retourne les Species avec un nom commun LIKE le paramètre fourni
    @Query("SELECT s FROM Species s WHERE LOWER(s.commonName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Species> findByCommonNameContainingIgnoreCase(@Param("name") String name);
}
    