package mypackage.repositories.Mastertables;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.mastertables.Locations;

public interface ILocations_repositories extends JpaRepository<Locations, Integer> {

}
