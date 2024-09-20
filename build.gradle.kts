import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
  // Core plugins
  java
  application
  // Community plugins
  id("org.jetbrains.kotlin.jvm") version "2.0.20"
  // fat jar
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
  mavenCentral()
}

group = "com.mamaliang"
version = "1.0.0-SNAPSHOT"

java {
}

val mainVerticleName = "com.mamaliang.npps.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"
val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
  mainClass.set(launcherClassName)
}

kotlin {
  // 来源于插件rg.jetbrains.kotlin.jvm，使用jdk21作为Kotlin编译器的工具链
  jvmToolchain(21)
}

val vertxVersion = "4.5.9"
val casVersion = "7.0.6"
val junitJupiterVersion = "5.9.1"

dependencies {
  // Kotlin 标准库（Standard Library）的 JDK 8 版本
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  // dependencyManagement
  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  implementation("io.vertx:vertx-core")
  implementation("io.vertx:vertx-web")
  implementation("io.vertx:vertx-lang-kotlin")
  implementation("io.vertx:vertx-lang-kotlin-coroutines")
  implementation("io.vertx:vertx-redis-client")

  // SLF4JLogDelegateFactory使用的spi的方式寻找实现类，所以log4j-slf4j-impl无法满足转而使用logback
  implementation("ch.qos.logback:logback-classic:1.5.7")

//  implementation("org.apereo.cas:cas-server-core:$casVersion")
//  implementation("org.apereo.cas:cas-server-support-redis-ticket-registry:${casVersion}")
//  implementation("org.apereo.cas:cas-server-support-redis-service-registry:${casVersion}")

  // 在cas-server-core的pom.xml中，很多依赖的scope为runtime，所以额外添加compileOnly
  // https://repo1.maven.org/maven2/org/apereo/cas/cas-server-core/7.0.6/cas-server-core-7.0.6.pom
//  compileOnly("org.apereo.cas:cas-server-core-authentication-api:$casVersion")
//  compileOnly("org.apereo.cas:cas-server-core-services-registry:$casVersion")

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
