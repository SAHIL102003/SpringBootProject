package mypackage.repositories.User;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.user.User_qualification;

public interface IUserqualificationRepositores extends JpaRepository<User_qualification, Integer> {

}
