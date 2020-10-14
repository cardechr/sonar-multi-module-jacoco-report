plugins {
    java
    kotlin("jvm")
    id("jacoco")
}


repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit", "junit", "4.12")
}
