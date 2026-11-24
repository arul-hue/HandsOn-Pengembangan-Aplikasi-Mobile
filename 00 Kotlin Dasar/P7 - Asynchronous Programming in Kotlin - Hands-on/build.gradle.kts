plugins {
    kotlin("jvm") version "1.9.24" apply false
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
    }

    dependencies {
        "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    }
}

// Latihan dan solusi berada di folder yang sama; kompilasi satu versi saja.
val versi = providers.gradleProperty("versi").getOrElse("solusi")
require(versi in listOf("latihan", "solusi")) {
    "Gunakan -Pversi=latihan atau -Pversi=solusi"
}

subprojects {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "17"
    }
    extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension> {
        sourceSets.getByName("main").kotlin {
            if (versi == "solusi") exclude("**/Latihan.kt")
            else exclude("**/Solusi.kt")
        }
        sourceSets.getByName("test").kotlin {
            if (versi == "solusi") include("**/*Solusi.kt")
            else exclude("**/*Solusi.kt")
        }
    }
}

subprojects {
    apply(plugin = "application")
    val kelasUtama = if (versi == "solusi") "SolusiKt" else "LatihanKt"
    extensions.configure<JavaApplication> {
        mainClass.set(kelasUtama)
    }
    tasks.named<Jar>("jar") {
        archiveFileName.set("${project.name}.jar")
        manifest { attributes["Main-Class"] = kelasUtama }
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        exclude("META-INF/*.SF", "META-INF/*.RSA", "META-INF/*.DSA")
        from({ configurations.getByName("runtimeClasspath").map {
            if (it.isDirectory) it else zipTree(it)
        } })
    }
}
