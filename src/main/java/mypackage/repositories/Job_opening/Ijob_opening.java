package mypackage.repositories.Job_opening;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.Job_Opening.Job_opening;

public interface Ijob_opening extends JpaRepository<Job_opening, Integer> {

}
