# wiitty-manager
wiitty-manager is a Web Interface to manage and launch scenarios Gherkin-Cucumber (UX Testing)

# Supported languages

The Web Interface is available in several languages (French and English for now). These are the currently supported languages:

* French
* English
* Czech (in progress)
* German (in progress)
* Spanish (in progress)
* Italian (in progress)
* Dutch (in progress)
	
If wiitty-manager not support your language? Please help us with a PR!

# Travis CI status

[![Build Status][travis-image]][travis-url]
[travis-image]: https://travis-ci.org/sgrillon14/wiitty-manager.svg?branch=master
[travis-url]: https://travis-ci.org/sgrillon14/wiitty-manager


# Technology

* HTML
* CSS
* AngularJS 1
* Spring Boot <b style='color:red'>(Caution: This application use 1.4.0.BUILD-SNAPSHOT)</b>
* Spring Web
* TestNG
* Travis CI

# Run Anywhere
![RunAnywhere](/screenshots/plateforme.png)

WIITTY Manager run anywhere the JVM does. Deploy standalone, in an app server, on a Cloud or all of the above.

# Production URL (Example)

UX (AngularJS 1):
* http://localhost:8089

![sampleAngularjs1](/screenshots/sampleAngularjs1.png)

use cases:
* http://localhost:8084/flags/api/countries return a list of countries (in English) 
* http://localhost:8084/flags/api/countries?lang=en return a list of countries (in English)
* http://localhost:8084/flags/api/countries?lang=fr return a list of countries (in French)
* http://localhost:8084/flags/api/fr return 200 OK and svg (French flag)
* http://localhost:8084/flags/api/fr/40/40 return 200 OK and png (French flag)

Errors cases:
* http://localhost:8084/flags/api/countries?lang=fakecode return a list of countries (in English)
* http://localhost:8084/flags/api/fakecode return 204 No Content
* http://localhost:8084/flags/api/fakecode/40/40 return 204 No Content

# JSON response (Example)



# License

BSD, See License.txt for details
