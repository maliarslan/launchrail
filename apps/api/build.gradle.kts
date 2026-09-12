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
    implementation("org.liquibase:liquibase-core:5.0.1")
    implementation("org.postgresql:postgresql:42.7.8")
    implementation(ktorLibs.serialization.kotlinx.json)
    implementation(ktorLibs.server.callLogging)
    implementation(ktorLibs.server.config.yaml)
    implementation(ktorLibs.server.contentNegotiation)
    implementation(ktorLibs.server.core)
    implementation(ktorLibs.server.netty)
    implementation(ktorLibs.server.statusPages)
    implementation(libs.logback.classic)

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

