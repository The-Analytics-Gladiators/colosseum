import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import net.researchgate.release.ReleaseExtension

plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.20"
    id("com.github.ben-manes.versions") version "0.47.0"
    id("io.gitlab.arturbosch.detekt").version("1.23.3")
    id("net.researchgate.release").version("3.0.2")
    `maven-publish`
}

group = "io.gladiators"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    api("org.web3j:core:4.10.3")
    api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.0")
    api("com.esaulpaugh:headlong:9.4.0")
    api("com.github.goodforgod:java-etherscan-api:2.1.0")
    api("com.github.ben-manes.caffeine:caffeine:3.1.8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.15.2")
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")

    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.6.1")
    testImplementation("io.kotest:kotest-property:5.6.1")
    testImplementation("io.kotest:kotest-framework-datatest:5.6.1")

}

val sourceJar by tasks.registering(Jar::class) {
    // Include Kotlin sources
    from(sourceSets["main"].kotlin)

}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            showExceptions = true
            showCauses = true
            showStandardStreams = true
            events = setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED)
            exceptionFormat = TestExceptionFormat.FULL
        }
    }
    afterReleaseBuild {
        dependsOn("publish")
    }
    withType<Jar> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}


publishing {
    publications {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/The-Analytics-Gladiators/colosseum")
                credentials {
                    username = "The-Analytics-Gladiators"
                    password = System.getenv("GITHUB_TOKEN") ?: System.getenv("TOKEN")
                }
            }
        }
        create<MavenPublication>("colosseum-lib") {
            from(components["kotlin"])
//            artifact(sourceJar)
        }
    }
}

configure<ReleaseExtension> {
    ignoredSnapshotDependencies = listOf("net.researchgate:gradle-release")
    failOnPublishNeeded = true
    with(git) {
        requireBranch = "main"
        // to disable branch verification: requireBranch.set(null as String?)
    }
}

sourceSets {
    main {
        java.srcDir("src/main/java")
        kotlin.srcDir("src/main/kotlin")
    }
}
