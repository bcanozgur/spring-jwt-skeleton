package indv.bozgur.spring.security.base.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserLoaderService {

    @Value("${security.jwt.username}")
    private String username;

    @Value("${security.jwt.password}")
    private String password;

    private Map<String, User> users = new HashMap<>();

    @PostConstruct
    public void initialize() {
        users.put(username, new User(username, password, new ArrayList<>()));
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }
}
