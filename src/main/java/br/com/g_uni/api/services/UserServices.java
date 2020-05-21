package br.com.g_uni.api.services;

import br.com.g_uni.api.controller.dto.UserDto;
import br.com.g_uni.api.controller.form.UserForm;
import br.com.g_uni.api.controller.form.update.UserFormUpdate;
import br.com.g_uni.api.model.User;
import br.com.g_uni.api.model.others.UserType;
import br.com.g_uni.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.Optional;

@Service
public class UserServices {
    // Observações:
    /*
    O trecho:
        return byId.map(user -> ResponseEntity.ok(new UserDto(user))).orElseGet(() -> ResponseEntity.notFound().build());
    é o equivalente a:
         if (userOptional.isPresent()) {
            return ResponseEntity.ok(new UserDto(byId.get()));
          } else {
            return ResponseEntity.notFound().build();
          }
     */

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    // Método para listar todos os usuarios - SEM FILTRO
    public Page<UserDto> listAllUsers(Pageable pagination) {
        Page<User> all = userRepository.findAll(pagination);
        return UserDto.convert(all);
    }

    // Método para listar todos os usuarios - COM FILTRO
    public Page<UserDto> listAllUsers(Pageable pagination, UserType userType) {
        Page<User> all = userRepository.findByUserType(userType, pagination);
        return UserDto.convert(all);
    }

    // Método para puxar um usuario pelo seu id
    public ResponseEntity<UserDto> getUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        return byId.map(user -> ResponseEntity.ok(new UserDto(user))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para puxar um usuario pelo seu nome
    public ResponseEntity<UserDto> getUserByName(String name) {
        Optional<User> byName = userRepository.findByName(name);
        return byName.map(user -> ResponseEntity.ok(new UserDto(user))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para cadastro de um novo usuario
    public ResponseEntity<UserDto> createUser(UserForm form, UriComponentsBuilder uriBuilder) {
        // Converte: UserForm -> User
        User user = form.convert();

        // Criptografa a senha antes de salvar no banco de dados
        user.setUserPassword(passwordEncoder.encode(user.getPassword()));

        // Salva o objeto User no banco de dados
        userRepository.save(user);

        // Cria o caminho da URI e retorna para o cliente
        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(user));
    }

    // Método para atualizar todos os dados de um usuario pelo seu id
    public ResponseEntity<UserDto> updateUserById(Long id, UserFormUpdate formUpdate) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            // Cripografa a senha do usuario antes de salvar no banco de dados
            formUpdate.setPassword(passwordEncoder.encode(formUpdate.getPassword()));

            // Atualiza as informações do usuario no banco de dados
            User user = formUpdate.update(id, userRepository);
            return ResponseEntity.ok(new UserDto(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para deletar um usuario pelo seu id
    public ResponseEntity<?> deleteUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            // Deleta usuario do banco de dados pelo id
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
