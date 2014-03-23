Description
===========

ParkingFriends allows a group of people to share private parking places planning absences and registering usage.
The Owner of a parking place get paid when a Friend uses his places.
Owners announcing absence dates in advance are the first in the list when searching for free parking places in a location.

Deploy on OpenShift
===================
With this process you can run your own instance of parkingfriends for free (thanks openshift)

1) Create an account at http://openshift.redhat.com/ and follow the Getting Started guide to install the OpenShift command line tools.

2) Create a JBoss Tomcat7 platform:
> rhc app create parkingfriends jbossews-2.0

 3) Add this upstream repo:
> cd parkingfriends
> git remote add upstream -m master git://github.com/exteso/parkingfriends.git
> git pull -s recursive -X theirs upstream master

 4) Push the repo upstream:
> git push

 5) That's it, you can now browse to your application at:
> http://parkingfriends-$yournamespace.rhcloud.com


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
