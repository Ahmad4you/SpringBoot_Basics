# Verwende ein offizielles Tomcat-Image als Basis
FROM tomcat:10.1-jdk17

# Setze den Maintainer
LABEL maintainer="test_email@example.com"

# Entferne die Standard-WAR-Datei aus dem Webapps-Verzeichnis
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Kopiere die WAR-Datei in das Tomcat-Webapps-Verzeichnis
COPY target/restapi-jersey-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Exponiere den Standard-Tomcat-Port
EXPOSE 8080

# app.war servlet container Starte Tomcat
CMD ["catalina.sh", "run"]
