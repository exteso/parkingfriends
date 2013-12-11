Installation & Setup
====================
#### Configure yeoman (http://yeoman.io)
> sudo npm install -g yo

#### Install project dependencies
> sudo npm install


Run & Demo
====================

#### from terminal launch
> mvn jetty:run (launch server)
> grunt server  (launch client dev environment)

#### open browser at:
- http://localhost:8080/ --> Load webpages from Tomcat
- http://localhost:9000/ --> Load webpages from grunt
- All calls to http://localhost:9000/app/ are redirected by grunt to http://localhost:8080/app/


License
==========
License of this application is GPL v3 (see LICENSE.txt)


How this application was built
==============================
Thanks to [jhipster](http://jhipster.github.io) for their great app generator.

> sudo npm install -g generator-jhipster