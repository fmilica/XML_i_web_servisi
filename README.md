### Sadrzaj

-   Opis
-   Pokretanje
-   Informacije o autorima

---

## Opis
Projektovana aplikacija informacionog sistema za pristup informacijama od javnog znacaja,
koja podrzava poslovne procese podnosenja zahteva za pristup informacijama od javnog znacaja
, koji podrzava poslovne procese podnosenja zahteva za pristup informacijama od javnog znacaja,
podnosenje zalbi zbog uskracenog prava na pristup informacijama od javnog znacaja i resavanja po zalbama
zbog uskracenog prava na pristup informacijama od javnog znacaja.

### Tehnologije

-   Spring Boot
-   Angular
-   TypeScript
-   Java
-   Maven

---

## Pokretanje

### Instalacija

Potrebno je da imate instalirano:
Java v8
[NodeJS](https://nodejs.org/download/release/v14.15.1/) v14.15.1

#### Backend

Potrebno je otvoriti backend projekte u razvojnom okruzenju koje podrzava Javu i Spring Boot framework ( na primer: Eclipse, Spting Tool Suite, IntelliJ..).
Sve .jar fajlove iz lib foldera u projektu uvezati u Build Path. Za instaliranje maven dependencies potrebno je pokrenuti Maven Run.. sa atributom maven clean install.
Aplikacije se pokrecu kao Java application.

Službenik:

  Projekat se nalazi u servisi/organ-vlasti/backend direktorijumu. Aplikacija se pokrece na portu 8082.

Poverenik:

  Projekat se nalazi u servisi/poverenik/backend direktorijumu. Aplikacija se pokrece na portu 8081.
  
Slanje mejla:

  Projekat se nalazi u servisi/eposta. Aplikacija se pokrece na portu 8886.


#### Frontend

Potrebno je otvoriti frontend projekte u razvojnom okruzenju koje podrzava Angular framework ( na primer: Visual Studio Code).
Potrebno je u terminalu otvorenom u projektnom direktorijumu prvo pokrenuti komandu npm install, a zatim pokrenuti aplikaciju komandom ng serve.

Sluzbenik:

  Projekat se nalazi u servisi/organ-vlasti/frontend direktorijumu. Aplikacija se pokrece na portu 4201.
  
Poverenik:

  Projekat se nalazi u servisi/poverenik/frontend direktorijumu. Aplikacija se pokrece na portu 4200.

---

#### XML baze

Preuzeti Apache TomEE plus aplikativni server: http://tomee.apache.org/download-ng.html

Preuzeti distribuciju eXist XML baze podataka: https://bintray.com/existdb/releases/exist/4.6.1/view
Preimenovati exist-x.x.x.war fajl u exist.war
Deployovati tj. kopirati exist.war u /webapps direktorijum TomEE-a dvaput, jednom sa nazivom existOrganVlasti.war, a drugi put sa nazivom existPoverenik.war
Pokrenuti aplikativni server i pristupiti dashboardu exist-a: http://localhost:8080/existOrganVlasti za bazu službenika, a http://localhost:8080/existPoverenik za bazu poverenika.

#### RDF baze

Preuzeti distribuciju Apache Jena Fuseki SPARQL servera: https://jena.apache.org/download/index.cgi#apache-jena-fuseki
Raspakovati apache-jena-fuseki-x.x.x.zip fajl
Deployovati tj. kopirati ekstrahovani fuseki.war u /webapps direktorijum TomEE-a dvaput, jednom sa nazivom fusekiOrganVlasti.war, a drugi put sa nazivom fusekiPoverenik.war
Pokrenuti aplikativni server i pristupiti admin interfejsu Fuseki servera: http://localhost:8080/fusekiOrganVlasti za bazu službenika, a http://localhost:8080/fusekiPoverenik za bazu poverenika.
XML baze moraju da imaju određenu strukturu foldera i fajlova. Neophodno je korisnici1.xml falj ubaciti u folder Korisnik u obe exist baze.

## Authors Info

-   [Ksenija Prćić](https://github.com/ksenija10)
-   [Eva Janković](https://github.com/evaj10)
-   [Milica Filipović](https://github.com/fmilica)
-   [Igor Lukić](https://github.com/cigor99)
