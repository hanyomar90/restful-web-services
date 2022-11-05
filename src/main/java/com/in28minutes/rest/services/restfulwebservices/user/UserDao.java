package com.in28minutes.rest.services.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDao {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;
    static{
        users.add(new User(++userCount, "hany", LocalDate.now().minusYears(33)));
        users.add(new User(++userCount, "amani", LocalDate.now().minusYears(29)));
        users.add(new User(++userCount, "hamza", LocalDate.now().minusYears(5)));
        users.add(new User(++userCount, "amr", LocalDate.now().minusYears(1)));
    }

    public List<User> findAll(){
        return users;
    }

    public User saveUser(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
