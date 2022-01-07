package com.nelioalves.curso.services;

import ch.qos.logback.core.net.server.Client;
import com.nelioalves.curso.domain.Cliente;
import com.nelioalves.curso.repositories.ClienteRepository;
import com.nelioalves.curso.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cli = repo.findByEmail(email);

        if(cli == null){
            throw new UsernameNotFoundException(email);
        }

        return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
    }
}
