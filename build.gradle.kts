plugins {
    kotlin("jvm") version "1.3.72"
    java
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "com.itskool"
version = "1.0"

repositories {
    mavenCentral()
}

val vertxVersion by extra("4.0.0")
val logbackClassicVersion by extra("1.2.3")

dependencies {
    implementation("io.vertx:vertx-web:$vertxVersion")
    implementation("ch.qos.logback:logback-classic:$logbackClassicVersion")
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.itskool.KubiaV1Kt"
    }
}

