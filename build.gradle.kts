plugins {
    groovy
    java
    kotlin("jvm") version "1.4.10"
    id("jacoco")
    id("org.sonarqube") version "3.0"
}

group = "org.cardechr"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.codehaus.groovy:groovy-all:2.3.11")
    implementation(kotlin("stdlib"))
    testImplementation("junit", "junit", "4.12")
}

sonarqube {
    properties {
        property("sonar.sourceEncoding", "UTF-8")
    }
}

val allTestCoverageFile = "$buildDir/jacoco/test.exec"
subprojects {
    sonarqube {
        properties {
            property("sonar.sources", "src")
            property("sonar.jacoco.xmlReportPath", allTestCoverageFile)
        }
    }
}

val jacocoTestReport = tasks.named<JacocoReport>("jacocoTestReport")
val jacocoMergeReports = tasks.register<JacocoMerge>("jacocoMergeReports")

tasks {
    named<JacocoReport>("jacocoTestReport") {
        reports {
            xml.isEnabled = true
        }
    }

    named<JacocoMerge>("jacocoMergeReports") {
        destinationFile = file(allTestCoverageFile)
        executionData(project.fileTree(projectDir).include("**/build/jacoco/test.exec'"))
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    finalizedBy(tasks["jacocoMergeReports"])
}