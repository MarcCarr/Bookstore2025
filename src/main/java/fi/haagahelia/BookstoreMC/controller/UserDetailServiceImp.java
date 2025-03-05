package fi.haagahelia.BookstoreMC.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.AuthorityUtils;

import fi.haagahelia.BookstoreMC.domain.User;
import fi.haagahelia.BookstoreMC.domain.UserRepository;

@Service
public class UserDetailServiceImp implements UserDetailsService {
    private final UserRepository repository;

    public UserDetailServiceImp(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User curruser = repository.findByUsername(username);
        if (curruser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        String role = curruser.getRole();
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        System.out.println(username + " has role: " + role);

        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(),
                AuthorityUtils.createAuthorityList(role));
        return user;
    }

}
