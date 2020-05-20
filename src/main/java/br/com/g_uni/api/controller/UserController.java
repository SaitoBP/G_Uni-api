package br.com.g_uni.api.controller;

import br.com.g_uni.api.controller.dto.UserDto;
import br.com.g_uni.api.controller.form.UserForm;
import br.com.g_uni.api.controller.form.update.UserFormUpdate;
import br.com.g_uni.api.model.User;
import br.com.g_uni.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController @RequestMapping("/user")
public class UserController {

    // Injeção de dependencia
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    // HTTP GET - Lista todos os usuarios
    // ToDo adicionar filtros de tipo de usuario
    @GetMapping @Cacheable(value = "listAllUsers")
    public Page<UserDto> listAllUsers(@PageableDefault(page = 0, size = 10, sort = "id",
                                                        direction = Sort.Direction.ASC) Pageable pagination) {
        Page<User> users = userRepository.findAll(pagination);
        return UserDto.convert(users);
    }

    // HTTP GET - Puxa um usuario pelo seu id
    @GetMapping("/id:{id}")
    public ResponseEntity <UserDto> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(value -> ResponseEntity.ok(new UserDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
        /*
            É o equivalente a:
            if (userOptional.isPresent()) {
               return ResponseEntity.ok(new UserDto(user.get()));
           } else {
                return ResponseEntity.notFound().build();
           }
        */
    }

    // HTTP GET - Puxa um usuario pelo seu username
    @GetMapping("/username:{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        Optional<User> userOptional = userRepository.findByName(username);
        return userOptional.map(value -> ResponseEntity.ok(new UserDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
        /*
            É o equivalente a:
            if (userOptional.isPresent()) {
               return ResponseEntity.ok(new UserDto(user.get()));
           } else {
                return ResponseEntity.notFound().build();
           }
        */
    }

    // HTTP POST - Posta um novo usuario
    @PostMapping @Transactional @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity<UserDto> postUser(@RequestBody @Valid UserForm form,
                                            UriComponentsBuilder uriBuilder) {
        User user = form.convert();

        // Antes de salvar o usuario, a senha é critografada
        user.setUserPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(user));
    }

    // HTTP PUT - Atualiza todos os dados de um usuario
    @PutMapping("/id:{id}") @Transactional @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity<UserDto> updateUserById(@PathVariable Long id,
                                                  @RequestBody @Valid UserFormUpdate userFormUpdate) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {

            // Antes de ser atualizado, a senha é criptografada
            userFormUpdate.setPassword(passwordEncoder.encode(userFormUpdate.getPassword()));

            User user = userFormUpdate.update(id, userRepository);
            return ResponseEntity.ok(new UserDto(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // HTTP DELETE - Deleta um usuario pelo seu id
    @DeleteMapping("/id:{id}") @Transactional @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
          return ResponseEntity.notFound().build();
        }
    }
}