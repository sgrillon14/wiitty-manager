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

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import wiitty.manager.model.Scenario;
import wiitty.manager.service.MavenService;
import wiitty.manager.service.ScenarioService;

/**
 * @author sgrillon
 */
@Controller
@RequestMapping("/wiitty/api")
public class ScenariosController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScenariosController.class);

    @Autowired
    private ScenarioService scenarioService;

    @Autowired
    private MavenService mavenService;

    /**
     * @param lang
     * @return all scenarios in a list.
     * @throws MalformedURLException
     */
    @CrossOrigin(origins = "http://localhost:8089")
    @RequestMapping(value = "/scenarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Scenario> getScenarios(@RequestParam(value = "lang", defaultValue = "en") String lang) throws MalformedURLException {
        LOGGER.debug("Get scenarios:  lang[{}]", lang);
        return scenarioService.getAllScenarios(lang);
    }

    /**
     * @param scenarioId
     * @return content of Scenario
     */
    @CrossOrigin(origins = "http://localhost:8089")
    @RequestMapping(value = "/scenario/{scenarioId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Scenario> getScenario(@PathVariable int scenarioId) {
        LOGGER.debug("getScenario : scenarioId[{}]", scenarioId);
        Scenario scenario = scenarioService.getScenario(scenarioId);
        return Optional.ofNullable(scenario).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    /**
     * @param scenarioId
     * @return content of Scenario
     */
    @CrossOrigin(origins = "http://localhost:8089")
    @RequestMapping(value = "/run/scenario/{scenarioId}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> runScenario(@PathVariable int scenarioId) {
        LOGGER.info("runScenario : scenarioId[{}]", scenarioId);
        mavenService.runScenario(scenarioId);
        return Optional.ofNullable("OK").map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

}
