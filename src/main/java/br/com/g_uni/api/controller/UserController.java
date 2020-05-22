package br.com.g_uni.api.controller;

import br.com.g_uni.api.controller.dto.UserDto;
import br.com.g_uni.api.controller.form.UserForm;
import br.com.g_uni.api.controller.form.update.UserUpdate;
import br.com.g_uni.api.model.others.UserType;
import br.com.g_uni.api.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController @RequestMapping("/user")
public class UserController {

    // Injeção de dependencia
    @Autowired private UserServices userServices;

    // HTTP GET - Lista todos os usuarios
    @GetMapping @Cacheable(value = "listAllUsers")
    public Page<UserDto> listAllUsers(@PageableDefault(page = 0, size = 10, sort = "id",
                                                       direction = Sort.Direction.ASC)
                                                       Pageable pagination,
                                      @RequestParam(required = false) UserType userType) {
        if (userType != null) {
            return userServices.listAllUsers(pagination, userType);

        } else {
            return userServices.listAllUsers(pagination);
        }
    }

    // HTTP GET - Puxa um usuario pelo seu id
    @GetMapping("/id:{id}")
    public ResponseEntity <UserDto> getUserById(@PathVariable Long id) {
        return userServices.getUserById(id);
    }

    // HTTP GET - Puxa um usuario pelo seu username
    @GetMapping("/username:{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return userServices.getUserByName(username);
    }

    // HTTP POST - Posta um novo usuario
    @PostMapping @Transactional @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity<UserDto> postUser(@RequestBody @Valid UserForm form,
                                            UriComponentsBuilder uriBuilder) {
        return userServices.createUser(form, uriBuilder);
    }

    // HTTP PUT - Atualiza todos os dados de um usuario
    @PutMapping("/id:{id}") @Transactional @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity<UserDto> updateUserById(@PathVariable Long id,
                                                  @RequestBody @Valid UserUpdate userUpdate) {
        return userServices.updateUserById(id, userUpdate);
    }

    // HTTP DELETE - Deleta um usuario pelo seu id
    @DeleteMapping("/id:{id}") @Transactional @CacheEvict(value = "listAllUsers", allEntries = true)
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        return userServices.deleteUserById(id);
    }
}