FROM tomcat:9.0
RUN mkdir project
COPY . /project
WORKDIR /project
RUN ./mvnw clean package
RUN cp target/SecondLabSpring.war /usr/local/tomcat/webapps/SecondLabSpring.war
CMD ["/usr/local/tomcat/bin/startup.sh"]