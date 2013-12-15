Description
===========

ParkingFriends allows a group of person to share private parking places planning absences and registering usage.
The Owner of a parking place get paid when a Friend uses his places.
Owners announcing absence dates in advance are the first in the list when searching for free parking places in a location.


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