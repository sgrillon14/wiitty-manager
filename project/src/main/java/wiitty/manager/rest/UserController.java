/*
 * Copyright (c) 2016 Stéphane GRILLON https://github.com/sgrillon14/wiitty-manager
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * * Neither the name of the <organization> nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package wiitty.manager.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wiitty.manager.model.Authenticate;
import wiitty.manager.model.User;
import wiitty.manager.service.UserService;

/**
 * @author sgrillon
 */
@Controller
@RequestMapping("/wiitty/api")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * @param scenarioId
     * @return content of Scenario
     */
    @CrossOrigin(origins = "http://localhost:8089")
    @RequestMapping(value = "/users/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getByUsername(@PathVariable String userName, HttpEntity<String> requestEntity) {
        String smUniversalId = requestEntity.getHeaders().getFirst("sm_universalid");
        String role = requestEntity.getHeaders().getFirst("ftapplicationroles");
        LOGGER.debug("getByUsername : userName[{}]", userName);
        User user;
        if (smUniversalId != null && !"".equals(smUniversalId) && "ADMIN".equals(role)) {
            // SSO user DBA (deactivate this part if you do not use SSO system)
            user = new User("", "", smUniversalId, "", "ADMIN");
        } else {
            // local user DBA
            user = userService.getByUsername(userName);
        }
        return Optional.ofNullable(user).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @CrossOrigin(origins = "http://localhost:8089")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Authenticate authenticate(HttpEntity<User> requestEntity) {
        String smUniversalId = requestEntity.getHeaders().getFirst("sm_universalid");
        String role = requestEntity.getHeaders().getFirst("ftapplicationroles");
        User user = requestEntity.getBody();
        LOGGER.debug("getByUsername : userName[{}] and password[{}]", user.getUserName(), user.getPassword());
        // local user DBA
        User userFind = userService.getByUsername(user.getUserName());
        if (userFind != null && userFind.getPassword().equals(user.getPassword())) {
            return new Authenticate(true, null);
        }
        // SSO user DBA (deactivate this part if you do not use SSO system)
        if (smUniversalId != null && !"".equals(smUniversalId) && "ADMIN".equals(role)) {
            return new Authenticate(true, null);
        }
        return new Authenticate();
    }

}
