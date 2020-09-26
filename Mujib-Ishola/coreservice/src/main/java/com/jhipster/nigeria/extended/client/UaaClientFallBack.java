package com.jhipster.nigeria.extended.client;

import com.jhipster.nigeria.extended.service.dto.UserDTO;
import com.jhipster.nigeria.extended.web.rest.vm.ManagedUserVM;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UaaClientFallBack implements UaaFeignClient {

    public UserDTO getUser() {
        return null;
    }

    @Override
    public UserDTO getUser(String login) {
        return null;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return null;
    }

    @Override
    public void registerProfile(ManagedUserVM managedUserVM) {

    }

    @Override
    public HashMap createAccount(HashMap managedUserVM) {
        return null;
    }

    @Override
    public UserDTO updateAccount(UserDTO managedUserVM) {
        return null;
    }

    @Override
    public void updateAccount(ManagedUserVM managedUserVM) {

    }

    @Override
    public UserDTO getUser(Long id) {
        return null;
    }

    @Override
    public List<UserDTO> getUsers(List<Long> ids) {
        return null;
    }

    @Override
    public void activateProfile(String key)  {
        throw new RuntimeException("User not found");
    }

    @Override
    public void requestPasswordReset(HashMap passwordReset) {

    }
}
