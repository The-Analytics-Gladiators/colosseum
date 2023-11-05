import com.google.protobuf.gradle.id
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import java.util.*

plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    id("com.google.protobuf") version "0.9.3"
    id("com.github.ben-manes.versions") version "0.47.0"
    id("io.gitlab.arturbosch.detekt").version("1.23.1")
    `maven-publish`
}

group = "io.gladiators"
version = Properties().apply {
    file("version.properties").inputStream().use { input ->
        load(input)
    }
}.getProperty("version")

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    api("org.web3j:core:4.10.2")

    api("org.jetbrains.kotlinx:kotlinx-coroutines-rx2:1.7.3")
    api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    api("org.rocksdb:rocksdbjni:8.3.2")
    api("redis.clients:jedis:4.4.0")
    api("io.arrow-kt:arrow-core:1.2.0")
    api("io.arrow-kt:arrow-fx-coroutines:1.2.0")
    api("com.esaulpaugh:headlong:9.4.0")
    api("com.github.goodforgod:java-etherscan-api:2.1.0")

    api("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")
    api("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.15.2")

    api("com.clickhouse:clickhouse-jdbc:0.4.6")
    api("com.clickhouse:clickhouse-http-client:0.4.6")

    api("io.grpc:grpc-kotlin-stub:1.3.0")
    api("io.grpc:grpc-protobuf:1.57.2")
    api("com.google.protobuf:protobuf-kotlin:3.23.4")
    api("com.github.ben-manes.caffeine:caffeine:3.1.8")
    implementation("io.github.oshai:kotlin-logging-jvm:5.0.1")

    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("org.reflections:reflections:0.10.2")

    testImplementation(kotlin("test"))
//    testImplementation("io.gladiators:liquid-test")

    testImplementation("io.kotest:kotest-runner-junit5:5.6.1")
    testImplementation("io.kotest:kotest-property:5.6.1")
    testImplementation("io.kotest:kotest-framework-datatest:5.6.1")

}

tasks.test {
    useJUnitPlatform()
    testLogging {
        showExceptions = true
        showCauses = true
        showStandardStreams = true
        events = setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED)
        exceptionFormat = TestExceptionFormat.FULL
    }
}

val sourceJar by tasks.registering(Jar::class) {
    // Include Kotlin sources
    from(sourceSets["main"].kotlin)

}

tasks.withType<Jar> { duplicatesStrategy = DuplicatesStrategy.EXCLUDE }


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

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.23.4"
    }

    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.56.1"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }

    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}


sourceSets {
    main {
        java.srcDir("src/main/java")
        kotlin.srcDir("src/main/kotlin")
    }
}

kotlin {
}
