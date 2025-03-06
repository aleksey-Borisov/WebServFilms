import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.9.20" // Обновите, если нужно
    kotlin("plugin.spring") version "1.9.20" // Обновите, если нужно
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21 // Обновили до Java 21

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starter Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Thymeleaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // Jackson для поддержки JSON в Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Kotlin Reflection
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Spring Boot Starter Test
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    // Kotlin Standard Library
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    // Дополнительные зависимости (если нужно)
    // implementation("org.springframework.boot:spring-boot-starter-data-jpa") // Для работы с JPA
    // implementation("org.springframework.boot:spring-boot-starter-security") // Для безопасности
    // implementation("mysql:mysql-connector-java") // Для подключения к MySQL
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "21" // Обновили до Java 21
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}