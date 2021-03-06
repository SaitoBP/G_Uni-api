package br.com.g_uni.api.config.security;

import br.com.g_uni.api.model.User;
import br.com.g_uni.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(s);
        if (user.isPresent()) {
            return user.get();
        }

        throw new UsernameNotFoundException("Dados incorretos");
    }
}
