import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    /**
     * Core plugins
     */
    java
    application
    /**
     * Community plugins
     */
    // kotlin version （target jvm,kotlin()==org.jetbrains.kotlin.＜...＞）
    kotlin("jvm") version "2.0.20"
    // fat jar
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.mamaliang"
version = "1.0.0-SNAPSHOT"
val mainVerticleName = "com.mamaliang.npps.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"
val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"
val vertxVersion = "4.5.9"
val junitJupiterVersion = "5.9.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

kotlin {
    // 来源于插件rg.jetbrains.kotlin.jvm，使用jdk8作为Kotlin编译器的工具链
    jvmToolchain(8)
}

application {
    mainClass.set(launcherClassName)
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin 标准库（Standard Library）的 JDK 8 版本
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // dependencyManagement
    implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
    implementation("io.vertx:vertx-core")
    implementation("io.vertx:vertx-lang-kotlin")
    implementation("io.vertx:vertx-lang-kotlin-coroutines")
    implementation("io.vertx:vertx-web")
    implementation("io.vertx:vertx-web-client")
    implementation("io.vertx:vertx-redis-client")

    // SLF4JLogDelegateFactory使用的spi的方式寻找实现类，所以log4j-slf4j-impl无法满足转而使用logback
    implementation("ch.qos.logback:logback-classic:1.5.7")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    testImplementation("io.vertx:vertx-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
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
//  jvmArgs = listOf(
//    // suspend=y:启动jvm时暂停，直至一个连接器连接上为止
//    "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:60599"
//  )
    args = listOf(
        "run",
        mainVerticleName,
        "--launcher-class=$launcherClassName",
        //    "--redeploy=$watchForChange",
        //    "--on-redeploy=$doOnChange"
    )
}
