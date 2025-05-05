plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    java
}

group = "com.MeLeia"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot dependencies


   // implementation 'org.springframework.boot:spring-boot-starter-validation' // Para validações
    //implementation 'org.springframework.boot:spring-boot-starter-security'  // Para autenticação e autorização
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")  // Se usar Thymeleaf para views


    implementation ("org.postgresql:postgresql") // Para PostgreSQL (substitua se usar outro banco)



    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Spring Boot Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Spring Boot JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // PostgreSQL
    runtimeOnly("org.postgresql:postgresql")

    // .env support (dotenv-java)
    implementation("io.github.cdimascio:dotenv-java:3.0.0")

    // Spring Boot Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
