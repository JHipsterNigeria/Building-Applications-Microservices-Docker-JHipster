package com.jhipster.nigeria.extended.client;

import com.jhipster.nigeria.client.AuthorizedFeignClient;
import com.jhipster.nigeria.extended.service.dto.UserDTO;
import com.jhipster.nigeria.extended.web.rest.vm.ManagedUserVM;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@AuthorizedFeignClient(name="uaa",fallback = UaaClientFallBack.class)
public interface UaaFeignClient {

    @GetMapping("/api/users/{login}")
    UserDTO getUser(@PathVariable(value = "login") String login) ;

    @GetMapping("/api/users/email/{email}")
    UserDTO getUserByEmail(@PathVariable(value = "email") String email) ;


    @PostMapping("/api/register")
    void registerProfile(@RequestBody ManagedUserVM managedUserVM);

    @PostMapping("/api/users")
    HashMap createAccount(@RequestBody HashMap managedUserVM);

    @PutMapping("/api/users")
    UserDTO updateAccount(@RequestBody UserDTO managedUserVM);

    @PostMapping("/api/account/update")
    void updateAccount(@RequestBody ManagedUserVM managedUserVM);

    @GetMapping("/api/users/id/{id}")
    UserDTO getUser(@PathVariable(value = "id") Long id) ;

    @GetMapping("/api/users/ids")
    List<UserDTO> getUsers(@RequestParam("ids") List<Long> ids);

    @GetMapping("api/activate")
    void activateProfile(@RequestParam(value = "key") String key) ;

    @PostMapping("api/account/reset-password/init")
    void requestPasswordReset(@RequestBody HashMap passwordReset) ;
}
