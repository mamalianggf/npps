import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.mamaliang"
version = property("version") as String

repositories {
    mavenCentral()
}

val mainVerticleName = "com.mamaliang.npps.manage.ManageVerticle"
val launcherClassName = "com.mamaliang.npps.Launcher"

//val watchForChange = "src/**/*"
//val doOnChange = "${projectDir}/gradlew classes"

application {
    mainClass.set(launcherClassName)
}

dependencies {
    implementation(platform("io.vertx:vertx-stack-depchain:${property("vertx")}"))
    implementation("io.vertx:vertx-web")
    implementation("io.vertx:vertx-web-client")
    implementation("io.vertx:vertx-auth-common")
    implementation("io.vertx:vertx-auth-ldap")
    implementation("io.vertx:vertx-auth-jwt")
    implementation("io.vertx:vertx-auth-sql-client")
    implementation("org.apache.commons:commons-lang3:${property("commonsLang3")}")
    implementation("org.bouncycastle:bcpkix-jdk18on:${property("bouncycastle")}")
    annotationProcessor("org.projectlombok:lombok:${property("lombok")}")
    compileOnly("io.vertx:vertx-codegen")
    compileOnly("org.projectlombok:lombok:${property("lombok")}")
    testImplementation("io.vertx:vertx-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter:${property("junit")}")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<ShadowJar> {
    archiveClassifier.set("fat")
    manifest {
        attributes(mapOf("Main-Verticle" to mainVerticleName))
    }
    mergeServiceFiles()
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(PASSED, SKIPPED, FAILED)
    }
}

tasks.withType<JavaExec> {
//    jvmArgs = listOf(
//        // suspend=y:启动jvm时暂停，直至一个连接器连接上为止
//        "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:60599"
//    )
    args = listOf(
        "run",
        mainVerticleName,
//        "--redeploy=$watchForChange",
        "--launcher-class=$launcherClassName"
//        "--on-redeploy=$doOnChange"
    )
}
