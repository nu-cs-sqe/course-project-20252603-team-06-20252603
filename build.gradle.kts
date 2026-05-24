plugins {
    id("java")
    id("checkstyle")
    id("com.github.spotbugs") version "6.1.11"
    jacoco
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

jacoco {
    toolVersion = "0.8.14"
    reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}

tasks.compileJava {
    options.release = 11
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = false
        csv.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
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