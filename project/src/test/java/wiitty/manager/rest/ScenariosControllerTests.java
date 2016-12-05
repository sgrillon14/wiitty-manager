/*
 * MIT License
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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import wiitty.manager.model.Scenario;

/**
 * Basic integration tests for ScenariosController.
 *
 * @author sgrillon
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ScenariosControllerTests extends AbstractTestNGSpringContextTests {

    @LocalServerPort
    private int port;

    @Test
    public void getAllScenarios() throws Exception {
        ResponseEntity<List> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/wiitty/api/scenarios", List.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().size()).isEqualTo(2);
    }

    @Test
    public void getAllScenariosEn() throws Exception {
        ResponseEntity<List> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/wiitty/api/scenarios?lang=en", List.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().size()).isEqualTo(2);
    }

    @Test
    public void getAllScenariosFr() throws Exception {
        ResponseEntity<List> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/wiitty/api/scenarios?lang=fr", List.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().size()).isEqualTo(2);
    }

    @Test
    public void getScenario() throws Exception {
        ResponseEntity<Scenario> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/wiitty/api/scenario/1", Scenario.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(1).isEqualTo(entity.getBody().getScenarioId());
        assertThat("DEMO").isEqualTo(entity.getBody().getTitle());
    }

    @Test
    public void getScenarioError() throws Exception {
        ResponseEntity<String> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/wiitty/api/scenario/0", String.class);
        assertThat(HttpStatus.NO_CONTENT).isEqualTo(entity.getStatusCode());
    }

    @Test
    public void runScenario() throws Exception {
        ResponseEntity<String> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/wiitty/api/run/scenario/1", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("OK");
    }

}
