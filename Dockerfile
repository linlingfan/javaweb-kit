 FROM java:8
 MAINTAINER ningning.ch@thelook.cn
 WORKDIR /app
 EXPOSE 80
 #ARG PROFILE
 #ENV spring.profiles.active $PROFILE
 #COPY application-prod.yml /app/
 #COPY application-dev.yml /app/
 COPY growth-api.jar /app/
 # docker 17.0+ HEALTHCHECK --timeout=1s --start-period=60s CMD curl -f -k http://localhost:80/ || exit 1
 CMD ["java","-Duser.timezone=GMT+8","-Djava.security.egd=file:/dev/./urandom","-jar","growth-api.jar","--server.port=80"]
