package mypackage.repositories.User;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.user.User_details;

public interface IUserdetailsRepositories extends JpaRepository<User_details, Integer>{

}
