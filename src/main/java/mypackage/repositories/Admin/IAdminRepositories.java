package mypackage.repositories.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.Admin.Admin_details;

public interface IAdminRepositories extends JpaRepository<Admin_details, Integer> {

}
