package mypackage.repositories.Mastertables;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.mastertables.Genders;

public interface IGender_repositories extends JpaRepository<Genders, Integer>{

}
