FROM tomcat:9.0
COPY target/SecondLabSpring.war /usr/local/tomcat/webapps/SecondLabSpring.war
RUN /usr/local/tomcat/bin/startup.sh