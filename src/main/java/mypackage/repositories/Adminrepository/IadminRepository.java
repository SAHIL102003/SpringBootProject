package mypackage.repositories.Adminrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.Admin.Admin_details;

public interface IadminRepository extends JpaRepository<Admin_details, Integer> {

}
