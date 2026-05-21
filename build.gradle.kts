plugins {
    id("java")
    id("checkstyle")
    id("com.github.spotbugs") version "6.1.11"
}

group = "nu.csse.sqe"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Source: https://mvnrepository.com/artifact/org.easymock/easymock
    testImplementation("org.easymock:easymock:5.4.0")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
}


tasks.compileJava {
    options.release = 11
}

tasks.test {
    useJUnitPlatform()
}

configure<CheckstyleExtension> {
    toolVersion = "10.12.5"
}

spotbugs {
    toolVersion.set("4.9.7")
    ignoreFailures.set(true)
}

tasks.withType<com.github.spotbugs.snom.SpotBugsTask>().configureEach {
    reports {
        create("html") { required.set(true) }
        create("xml") { required.set(false) }
    }
}