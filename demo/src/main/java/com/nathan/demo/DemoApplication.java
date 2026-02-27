package com.nathan.demo;

import com.nathan.demo.model.Animal;
import com.nathan.demo.model.Person;
import com.nathan.demo.model.Species;
import com.nathan.demo.repository.SpeciesRepository;
import com.nathan.demo.repository.PersonRepository;
import com.nathan.demo.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private SpeciesRepository speciesRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AnimalRepository animalRepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("=== Test du SpeciesRepository ===");

		// 1. findAll - Afficher toutes les espèces
		System.out.println("\n--- findAll ---");
		List<Species> allSpecies = speciesRepository.findAll();
		for (Species s : allSpecies) {
			System.out.println(s);
		}
		System.out.println("Nombre d'espèces : " + allSpecies.size());

		// 2. save - Créer une nouvelle espèce
		System.out.println("\n--- save ---");
		Species hamster = new Species("Hamster", "Mesocricetus auratus");
		hamster = speciesRepository.save(hamster);
		System.out.println("Espèce créée : " + hamster);

		// 3. findById - Rechercher l'espèce créée par son id
		System.out.println("\n--- findById ---");
		Optional<Species> found = speciesRepository.findById(hamster.getId());
		if (found.isPresent()) {
			System.out.println("Espèce trouvée : " + found.get());
		} else {
			System.out.println("Espèce non trouvée !");
		}

		// 4. delete - Supprimer l'espèce et vérifier
		System.out.println("\n--- delete ---");
		speciesRepository.delete(hamster);
		List<Species> afterDelete = speciesRepository.findAll();
		System.out.println("Nombre d'espèces après suppression : " + afterDelete.size());

		System.out.println("\n=== Fin des tests ===");
		
		// ============================================
		// 1. Tests SpeciesRepository
		// ============================================
		System.out.println("=== 1. SpeciesRepository ===");

		// findFirstByCommonName
		System.out.println("\n--- findFirstByCommonName('Chat') ---");
		Species chat = speciesRepository.findFirstByCommonName("Chat");
		System.out.println(chat);

		// findByLatinNameContainingIgnoreCase
		System.out.println("\n--- findByLatinNameContainingIgnoreCase('canis') ---");
		List<Species> speciesList = speciesRepository.findByLatinNameContainingIgnoreCase("canis");
		speciesList.forEach(System.out::println);

		System.out.println("\n--- findByLatinNameContainingIgnoreCase('catus') ---");
		speciesList = speciesRepository.findByLatinNameContainingIgnoreCase("catus");
		speciesList.forEach(System.out::println);

		// ============================================
		// 2. Tests PersonRepository
		// ============================================
		System.out.println("\n=== 2. PersonRepository ===");

		// findByLastnameOrFirstname
		System.out.println("\n--- findByLastnameOrFirstname('Lamarque', 'Sophie') ---");
		List<Person> persons = personRepository.findByLastnameOrFirstname("Lamarque", "Sophie");
		persons.forEach(System.out::println);

		// findByAgeGreaterThanEqual
		System.out.println("\n--- findByAgeGreaterThanEqual(45) ---");
		List<Person> olderPersons = personRepository.findByAgeGreaterThanEqual(45);
		olderPersons.forEach(System.out::println);

		// ============================================
		// 3. Tests AnimalRepository
		// ============================================
		System.out.println("\n=== 3. AnimalRepository ===");

		// findBySpecies
		System.out.println("\n--- findBySpecies(Chat) ---");
		Species chatSpecies = speciesRepository.findFirstByCommonName("Chat");
		List<Animal> cats = animalRepository.findBySpecies(chatSpecies);
		cats.forEach(System.out::println);

		// findByColorIn
		System.out.println("\n--- findByColorIn(['Blanc', 'Noir']) ---");
		List<Animal> animals = animalRepository.findByColorIn(List.of("Blanc", "Noir"));
		animals.forEach(System.out::println);

		System.out.println("\n=== Fin des tests TP04 ===");

		System.out.println("\n--- findAllOrderByCommonNameAsc ---");
		List<Species> orderedSpecies = speciesRepository.findAllOrderByCommonNameAsc();
		orderedSpecies.forEach(System.out::println);

		System.out.println("\n--- findByCommonNameContainingIgnoreCase('Cat') ---");
		List<Species> speciesLikeCat = speciesRepository.findByCommonNameContainingIgnoreCase("Cat");
		speciesLikeCat.forEach(System.out::println);

		System.out.println("\n--- findByAgeBetween(30, 50) ---");
		List<Person> personsBetweenAges = personRepository.findByAgeBetween(30, 50);
		personsBetweenAges.forEach(System.out::println);

		System.out.println("\n--- findByAnimals_Id(1) ---");
		List<Person> personsWithAnimal = personRepository.findByAnimals_Id(1);
		personsWithAnimal.forEach(System.out::println);

		System.out.println("\n--- countBySex('M') ---");
		long count = animalRepository.countBySex("M");
		System.out.println("Nombre d'animaux de sexe masculin : " + count);

		System.out.println("\n--- existsPersonHavingAnimalWithName('Médor') ---");
		boolean exists = animalRepository.existsPersonHavingAnimalWithName("Médor");
		System.out.println("Existe-t-il un animal nommé 'Médor' appartenant à une personne ? " + exists);
	}
}
