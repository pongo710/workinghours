package de.schilling.workinghours.user;

import org.junit.Test;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class UserServiceImplTest {

    @Test
    public void testLoadUserByUsername() throws Exception {
        System.out.println(new StandardPasswordEncoder().encode("sicherheit"));
    }
}