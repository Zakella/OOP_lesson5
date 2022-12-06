package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;
import java.util.regex.Pattern;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        validateUserData(user);
        repository.CreateUser(user);
    }

    public User readUser(String userId)  {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new IllegalStateException("User not found");
    }
    public List<User> readUsers(){
        return repository.getAllUsers();
    }

    public void validateUserData(User user) {
        if ((user.getFirstName()).isEmpty() || (user.getLastName()).isEmpty() ||
                (user.getPhone()).isEmpty()) throw new IllegalStateException("Fields are empty");
        validatePhone(user);
    }

    public void validatePhone(User user){
        String phone = user.getPhone();
        if (!phone.matches("\\+7\\d*")){
            throw new IllegalStateException("Invalid phone number ");
        }

    }

    public void editUser(User user){
        validateUserData(user);
        repository.updateUser(user);
    }

    public void deleteUser(String userId) {
        repository.deleteUser(userId);
    }

}
