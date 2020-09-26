package com.jhipster.nigeria.extended.service;
import com.jhipster.nigeria.extended.client.UaaFeignClient;
import com.jhipster.nigeria.extended.service.dto.UserDTO;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private UaaFeignClient uaaFeignClient;



    public UserService(UaaFeignClient uaaFeignClient) {
        this.uaaFeignClient = uaaFeignClient;
    }


    public Optional<UserDTO> getUserWithAuthoritiesByLogin(String login) {
        try {
            return Optional.ofNullable(uaaFeignClient.getUser(login));
        } catch (FeignException.FeignClientException e) {
            if (!Integer.valueOf(HttpStatus.NOT_FOUND.value()).equals(e.status())) {
                //  throw new Exception("Error processing request");
            }
        }
        return Optional.ofNullable(null);
    }

    public UserDTO getUserByLogin(String login) {
        return getUserWithAuthoritiesByLogin(login).orElse(null);
    }

    public Optional<UserDTO> getUserWithAuthoritiesByEmail(String login) {
        try {
            return Optional.ofNullable(uaaFeignClient.getUserByEmail(login));
        } catch (FeignException.FeignClientException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(null);
    }

    public UserDTO getUserByEmail(String email) {
        return getUserWithAuthoritiesByEmail(email).orElse(null);
    }

}
