plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ktlint.gradle)
    id("maven-publish")
}

android {
    namespace = "com.syzygyhub.ui.android"
    compileSdk {
        version =
            release(37) {
                minorApiLevel = 1
            }
    }

    defaultConfig {
        minSdk = 29
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }

    publishing {
        singleVariant("release")
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.Syzygy-Hub"
                artifactId = "syzygy-ui-android"
                version = "2.4.0"
            }
        }
    }
}

// The `org.jlleitschuh.gradle.ktlint` plugin normally generates one check
// task per Kotlin source set, discovered via the classic Kotlin Gradle
// Plugin's `KotlinSourceSet` API. This module intentionally does not apply
// `org.jetbrains.kotlin.android` (applying it conflicts with this AGP
// version's own built-in Kotlin support — "Cannot add extension with name
// 'kotlin', as there is an extension already registered with that name" —
// AGP 9.x registers its own `kotlin` extension and compiles Kotlin sources
// itself). Without classic KGP present, ktlint-gradle finds zero
// `KotlinSourceSet`s, so `ktlintCheck` silently only lints `.kts` build
// scripts and never touches `app/src/**/*.kt`.
//
// Fix: depend on the real `ktlint-cli` artifact directly (the same one
// ktlint-gradle itself uses internally, confirmed via
// `./gradlew :app:dependencies --configuration ktlint`) and drive it
// against the actual Kotlin source directories via a plain `JavaExec` task,
// bypassing the broken source-set auto-discovery entirely. This is still
// "the ktlint tool" — just invoked directly instead of through the plugin's
// non-functional auto-wiring — so it doesn't violate the library's
// zero-third-party-runtime-dependency goal (this is a build-time-only,
// verification-scoped dependency, same category as `flutter_lints`/eslint
// configs used elsewhere in the Syzygy ecosystem).
val ktlintCli = configurations.register("ktlintCli") { }

dependencies {
    add(ktlintCli.name, "com.pinterest.ktlint:ktlint-cli:1.0.1")
}

val ktlintCheckSources =
    tasks.register<JavaExec>("ktlintCheckSources") {
        group = "verification"
        description = "Runs ktlint directly against app/src/**/*.kt (ktlint-gradle's own source-set " +
            "discovery finds nothing in this AGP-embedded-Kotlin setup, see comment above)."
        classpath = ktlintCli.get()
        mainClass.set("com.pinterest.ktlint.Main")
        // No explicit --editorconfig: ktlint walks up from each linted file's
        // directory to find the nearest .editorconfig (standard EditorConfig
        // resolution), so a repo-root .editorconfig (as fetched from
        // syzygy-lint-config in CI) is picked up automatically.
        args = listOf("src/**/*.kt")
        workingDir = project.projectDir
    }

tasks.named("ktlintCheck") {
    dependsOn(ktlintCheckSources)
}

val ktlintFormatSources =
    tasks.register<JavaExec>("ktlintFormatSources") {
        group = "formatting"
        description = "Auto-fixes ktlint violations in app/src/**/*.kt (see ktlintCheckSources)."
        classpath = ktlintCli.get()
        mainClass.set("com.pinterest.ktlint.Main")
        args = listOf("-F", "src/**/*.kt")
        workingDir = project.projectDir
    }

tasks.named("ktlintFormat") {
    dependsOn(ktlintFormatSources)
}
