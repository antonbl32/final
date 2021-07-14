package it.free.final_spring.security;

import it.free.final_spring.entity.UserEntity;
import it.free.final_spring.exception.NotFoundUserException;
import it.free.final_spring.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity=userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException("User dosn't exist"));
        return SecurityUser.fromUser(userEntity);
    }
}
