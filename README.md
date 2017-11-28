# projet_xs_RestServer
services rest xs

### Required configuration ###
* JDK 1.7 or above
* Maven 3
* Git
* Tomcat 7 or above

## Running  ##

### Compilation ###
```
mvn clean install
```
This command line creates the executable file **war** in the /target directory.

### Running ###
put the war file in tomcat server

run as :
```
http://[tomcat host]:[tomcat port]/RestServerXSpeedIt-0.0.1-SNAPSHOOT/api/XSpeedIt/{chaineEntree}
ou
http://[tomcat host]:[tomcat port]/RestServerXSpeedIt/api/XSpeedIt/{chaineEntree}
ou
Autre selon la configuration du context
```
