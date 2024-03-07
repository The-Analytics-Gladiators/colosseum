import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import net.researchgate.release.ReleaseExtension

plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
    id("com.github.ben-manes.versions") version "0.50.0"
    id("io.gitlab.arturbosch.detekt").version("1.23.5")
    id("net.researchgate.release").version("3.0.2")
    `maven-publish`
}

group = "io.gladiators"

repositories {
    mavenCentral()
    mavenLocal()
}

val ktorVersion = "2.3.6"
val resilience4jVersion = "2.2.0"

dependencies {
    api("org.web3j:core:4.10.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.2")
    api("com.esaulpaugh:headlong:10.0.2")
    api("com.github.goodforgod:java-etherscan-api:2.1.0")
    api("com.github.ben-manes.caffeine:caffeine:3.1.8")
    api("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-auth:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.15.2")
    implementation("io.github.oshai:kotlin-logging-jvm:6.0.3")

    testImplementation(kotlin("test"))
    testImplementation("io.github.resilience4j:resilience4j-kotlin:${resilience4jVersion}")
    testImplementation("io.github.resilience4j:resilience4j-retry:${resilience4jVersion}")
    testImplementation("io.github.resilience4j:resilience4j-ratelimiter:${resilience4jVersion}")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-property:5.8.0")
    testImplementation("io.kotest:kotest-framework-datatest:5.8.0")

}

val sourceJar by tasks.registering(Jar::class) {
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
