/*
 * @Author Sam Newball
 * 11/20/23
 */

plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.google.guava:guava:32.1.1-jre")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
        mainClass.set("av.app.App")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
