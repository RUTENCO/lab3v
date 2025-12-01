# Usar la imagen base de OpenJDK
FROM eclipse-temurin:21-jre-jammy

  
  # Establecer el directorio de trabajo
WORKDIR /app
  
  # Copiar el archivo JAR generado por Spring Boot
COPY target/lab3v-0.0.1-SNAPSHOT.jar app.jar
  
  # Exponer el puerto de la aplicación
EXPOSE 8089
  
  # Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]