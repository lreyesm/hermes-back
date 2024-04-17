GENERACION Y COMPILACION DE UN NUEVO PROYECTO JAVA BASADO EN OINARRI
====================================================================

ANTIGUO NO UTILIZAR
----------------------------------------------

* Es importante renombrar tanto las carpetas como el contenido de algunos ficheros para reflejar el nombre de la aplicación.

https://gitlab-ekide.eroski.es/eroski/gstderk/gsample/psample-back

https://gitlab-ekide.eroski.es/eroski/gstderk/gscaffolds/pstarters-libs-java

git clone proyectos anteriores.

En la raiz de la carpeta del proyecto deben estar las carpetas:

psample-back (o el nombre de la nueva app)
pstarters-libs-java (las librerías autoconfiguradas para sprint)

Para la compilación de las librerías es necesario acceder a cada una de ellas y hacer un mvn clean install
Pero antes hay que instalar previamente la versión de java Temurin openjdk versión 17 https://adoptium.net/es/temurin/releases/?version=17 idealmente instalar en C:\EroskiSAM\software\java\jdk11.0.17
y poner como variable de entorno:

SET JAVA_HOME=C:\EroskiSAM\software\java\jdk-17.0.9+9 (La carpeta donde se haya instalado)

En las librerías y en el fichero properties está la configuración de cada una de ellas.

Después de la compilación de las librerías, vamos a la carpeta del proyecto y hacemos un mvn clean install 

NUEVO, UTILIZAR DE ESTA FORMA
----------------------------------------------

https://artifactory-ekide.eroski.es/ui/repos/tree/General/eroski-maven-dev-local/es/eroski/oinarri-back-archetype/0.1.0-SNAPSHOT

Antes de ejecutar el comando hay que añadir al settings.xml el repositorio del archetype que es donde se va a encontrar. Si cambia en un futuro hay que cambiar la url.

	<repository>
		<id>archetype</id>
		<url>https://artifactory-ekide.eroski.es/artifactory/eroski-maven-release-local/</url>
		<releases>
		<enabled>true</enabled>
		<checksumPolicy>fail</checksumPolicy>
		</releases>
		<snapshots>
		<enabled>true</enabled>
		<checksumPolicy>warn</checksumPolicy>
		</snapshots>
	</repository>

Comando para generar nuevo_proyecto SPB a partir del artifactory

C:\EroskiSAM\software\maven\apache-maven-3.8.4\bin\mvn org.apache.maven.plugins:maven-archetype-plugin:3.0.0:generate -DarchetypeGroupId=es.eroski -DarchetypeArtifactId=oinarri-back-archetype -DarchetypeVersion=0.1.0-SNAPSHOT -DgroupId=es.eroski -DartifactId=nuevoproyecto -Dversion=1.0-SNAPSHOT

Los últimos 3 parámetros cambiar a gusto, ya que crean el package de java así como la versión

El nuevo proyecto tiene muchas dependencias en el POM (tienen que ver con el modo antiguo de generar el proyecto) ya con configuración. 
En teoría si se cambia la versión solo sería necesario actualizar el proyecto para bajar las nuevas versiones de dichas dependencias.

La versión de java a utilizar es Temurin openjdk versión 17 https://adoptium.net/es/temurin/releases/?version=17 idealmente instalar en C:\EroskiSAM\software\java\jdk11.0.17
y poner como variable de entorno:

SET JAVA_HOME=C:\EroskiSAM\software\java\jdk-17.0.9+9 (La carpeta donde se haya instalado)

Despues vamos a la carpeta del proyecto y hacemos un mvn clean install 

Arrancar en local como:

C:\EroskiSAM\software\java\jdk-17.0.9+9\bin\java -Dspring.profiles.active=dev -jar ./target/PHERMES-back-1.0-SNAPSHOT.jar

VARIABLES DE ENTORNO
--------------------

${LDAP_URL}
${LDAP_BASE}
${LDAP_USERDN}
${LDAP_PASSWORD}

${POSTGRESQL_USERNAME}
${POSTGRESQL_PASSWORD}
${POSTGRESQL_URL}

${APPPRETEST}

${ADMIN_USER_CREDENTIALS}

dev.bat
--------
set LDAP_URL=ldap://metaldap.eroski.es:389
set LDAP_BASE=o=eroskiusers
set LDAP_USERDN
set LDAP_PASSWORD

set POSTGRESQL_URL=jdbc:postgresql://172.22.44.116:5001/hermes
set POSTGRESQL_USERNAME=hermesadm
set POSTGRESQL_PASSWORD=wHkPo3F@1GtvDEl

set APPPRETEST=HPESCA_HPESCA

set ADMIN_USER_CREDENTIALS=12345678

DOCKER
------

Pasos para instalar ubuntu como wsl

	- Cmd
	- wsl --install
	- Reiniciar máquina
	- Se termina de instalar ubuntu
		○ Usuario
		○ Clave

Instalar para windows 10 https://docs.docker.com/engine/install/ubuntu/

	- Instalar docker
		○ sudo apt-get update
		○ sudo apt-get install ca-certificates curl gnupg
		○ sudo install -m 0755-d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg |sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
sudo chmod a+r /etc/apt/keyrings/docker.gpg
		○ # Add the repository to Apt sources:echo\
"deb [arch="$(dpkg --print-architecture)" signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  "$(. /etc/os-release &&echo"$VERSION_CODENAME")" stable"|\
		○ sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
		○ sudo apt-get update
		○ sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

	- Verificar instalación
		○ sudo docker run hello-world

GENERACION DE LA IMAGEN DE LA APP SPB
-------------------------------------
Para generación de la imagen se utiliza el fichero dockerfile que ya está configurado.

Para la creacion nos vamos a ubuntu y movemos a la carpeta del proyecto: /mnt/c/EroskiSAM/source/hermes/spb_hermes_back/phermes-back

Desde alli ejecutamos el comando: 

- Sin especificar fichero

sudo docker build . --tag="hermes_1.0.0" --build-arg="ARTIFACT_ID=phermes_back" --build-arg="VERSION=1.0-SNAPSHOT"

- Especificando fichero con variables de entorno

sudo docker build . --file="./Dockerfile_env" --tag="hermes_1.0.0" --build-arg="ARTIFACT_ID=phermes_back" --build-arg="VERSION=1.0-SNAPSHOT"

que creará una imagen con el nombre "hermes_1.0.0"

La imagen generada se puede ver con el comando:

sudo docker image list 

pacosal@IBLAESBILC06670:/mnt/c/EroskiSAM/source/hermes/phermes-back$ sudo docker image list
REPOSITORY     TAG       IMAGE ID       CREATED          SIZE
hermes_1.0.0   latest    a49f4ec01c91   13 minutes ago   377MB
hello-world    latest    9c7a54a9a43c   7 months ago     13.3kB
pacosal@IBLAESBILC06670:/mnt/c/EroskiSAM/source/hermes/phermes-back$

EJECUCION DE LA IMAGEN 
----------------------

- Con perfil por defecto "local"

sudo docker run "hermes_1.0.0" -p 8080:8080

- Con profile activo=dev y variables de entorno

sudo docker run -p 8080:8080 -e "LDAP_URL=ldap://metaldap.eroski.es:389" -e "LDAP_BASE=o=eroskiusers" -e "POSTGRESQL_URL=jdbc:postgresql://172.22.44.116:5001/hermes" -e "POSTGRESQL_USERNAME=hermesadm" -e "POSTGRESQL_PASSWORD=wHkPo3F@1GtvDEl" -e "APPPRETEST=HPESCA_HPESCA" -e "ADMIN_USER_CREDENTIALS=12345678" -e "SPRING_PROFILES_ACTIVE=dev" "hermes_1.0.0"

para que nos devuelva el control: sudo docker run --detach "hermes_1.0.0" 

para que el puerto del docker este accesible desde windows 

sudo docker run -p 127.0.0.1:8000:8080 "hermes_1.0.0"

Atención que se expone el puerto 8080 a través del puerto 8000, de modo que la url de servicio es:

http://localhost:8000/swagger-ui/index.html


CONTENEDORES
---------------------------------

En ejecucion: sudo docker ps

Todos: sudo docker ps -all

Para parar un contenedor: sudo docker stop 2da6efb44ae9

Entrar en una instancia corriendo

sudo docker exec -ti 213aaba8297b sh

BBDD
---------------------------------

os conectais por putty a la máquina hpesca desa
y ejecutar el comando: ssh -L 172.22.44.116:5001:172.30.142.64:5432 hpesca@slx00012192.eroski.es
os pediré la clave del usuario hpesca
luego os conectais al puerto 172.22.44.116:5001 con jdbc

TEST
-------------------------------------
Compilación y ejecución con un profile determinado

c:\EroskiSAM\software\maven\apache-maven-3.8.4\bin\mvn clean install -DargLine="-Dspring.profiles.active=dev"  

c:\EroskiSAM\software\maven\apache-maven-3.8.4\bin\mvn test-compile org.pitest:pitest-maven:mutationCoverage

