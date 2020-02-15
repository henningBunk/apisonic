plugins {
    kotlin("jvm") version "1.3.70-eap-42"
    kotlin("kapt") version "1.3.61"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }

    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.squareup.retrofit2:retrofit:2.7.1")
    implementation("com.squareup.moshi:moshi:1.9.2") //Todo needed?
//    implementation("com.squareup.moshi:moshi-kotlin:1.9.2") //Uses Kotlin-reflect
    implementation("com.squareup.okhttp3:logging-interceptor:4.3.1")
    implementation("com.squareup.retrofit2:converter-moshi:2.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")

    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.0")

    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.9.2")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}