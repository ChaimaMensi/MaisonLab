package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Méthode pour s'inscrire (sign up)
    public User signUp(User user) {
        // Vérification si l'email ou le username existe déjà
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé !");
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Nom d'utilisateur déjà utilisé !");
        }

        // Enregistrement direct du mot de passe sans hachage
        return userRepository.save(user);
    }

    // Méthode pour se connecter (sign in)
    public User signIn(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Nom d'utilisateur non trouvé !");
        }

        User user = optionalUser.get();
        // Vérification du mot de passe (comparaison directe)
        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect !");
        }

        return user; // Retourne l'utilisateur si authentifié
    }
}
