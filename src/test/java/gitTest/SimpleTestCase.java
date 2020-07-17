package gitTest;
import models.User;
import org.springframework.web.client.RestTemplate;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SimpleTestCase { 
    @Test
    public void testUserInfoResponse(){
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject("https://api.github.com/users/julikor-103", User.class);
        System.out.println(user);
        assertTrue(user.getLogin().contains("julikor-103"));
    }

    @Test
    public void testFollowers(){
        RestTemplate restTemplate = new RestTemplate();
        User [] followers = restTemplate.getForObject("https://api.github.com/users/julikor-103/followers", User[].class);
        assertTrue(followers.length ==0);
    }
}
