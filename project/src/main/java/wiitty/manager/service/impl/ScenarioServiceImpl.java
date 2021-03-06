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
package wiitty.manager.service.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import wiitty.manager.model.Scenario;
import wiitty.manager.service.ScenarioService;

/**
 * @author sgrillon
 */
@Component
public class ScenarioServiceImpl implements ScenarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScenarioServiceImpl.class);

    @Value("$i18n.config")
    private String i18nPath;

    /**
     * {@inheritDoc}
     *
     * @throws MalformedURLException
     */
    @Override
    public List<Scenario> getAllScenarios(String lang) throws MalformedURLException {
        ResourceBundle labels = ResourceBundle.getBundle("i18n" + File.separator + "labels", new Locale(lang));

        LOGGER.debug("getAll:  lang[{}]", lang);
        List<Scenario> scenarios = new ArrayList<>();
        scenarios.add(new Scenario(1, labels.getString("DEMO")));
        scenarios.add(new Scenario(2, labels.getString("DEMO2")));
        return scenarios;
    }

    @Override
    public Scenario getScenario(int scenarioId) {
        if (scenarioId == 1) {
            return new Scenario(1, "DEMO");
        } else if (scenarioId == 2) {
            return new Scenario(2, "DEMO2");
        }
        return null;
    }

}
