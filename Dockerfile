FROM openjdk:21
COPY target/my_internet_shop_pj-0.1.jar my_internet_shop_pj-0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "my_internet_shop_pj-0.1.jar"]