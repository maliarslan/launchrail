plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(ktorLibs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
}

group = "com.launchrail"
version = "1.0.0-SNAPSHOT"

application {
    mainClass = "com.launchrail.ApplicationKt"
}

kotlin {
    jvmToolchain(21)
}
dependencies {
    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.java.time)
    implementation(libs.liquibase.core)
    implementation(ktorLibs.serialization.kotlinx.json)
    implementation(ktorLibs.server.callLogging)
    implementation(ktorLibs.server.config.yaml)
    implementation(ktorLibs.server.contentNegotiation)
    implementation(ktorLibs.server.core)
    implementation(ktorLibs.server.netty)
    implementation(ktorLibs.server.statusPages)
    implementation(libs.logback.classic)
    implementation(libs.postgresql)

    testImplementation(kotlin("test"))
    testImplementation(ktorLibs.client.contentNegotiation)
    testImplementation(ktorLibs.server.testHost)
}

tasks.register<JavaExec>("runMigrations") {
    group = "database"
    description = "Run Liquibase migrations against the configured database."

    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.launchrail.infrastructure.persistence.liquibase.MigrationMainKt")
}
