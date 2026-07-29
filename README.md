# android-ui-library

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Platform](https://img.shields.io/badge/Android-API%2029%2B-3DDC84?logo=android&logoColor=white)](https://developer.android.com)
[![JitPack](https://jitpack.io/v/aks5686/android-ui-library.svg)](https://jitpack.io/#aks5686/android-ui-library)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![CI](https://github.com/aks5686/android-ui-library/actions/workflows/android.yml/badge.svg)](https://github.com/aks5686/android-ui-library/actions/workflows/android.yml)

Production-ready Jetpack Compose component library with Material 3 design tokens, Dark Mode, and zero third-party dependencies.

## Requirements
- Android API 29+
- Kotlin 2.x
- Jetpack Compose

## Installation

### Step 1 — Add JitPack to your project
In your root `settings.gradle.kts`:
```kotlin
dependencyResolutionManagement {
    repositories {
        maven { url = uri("https://jitpack.io") }
    }
}
```

### Step 2 — Add the dependency
In your app's `build.gradle.kts`:
```kotlin
dependencies {
    implementation("com.github.aks5686:android-ui-library:v1.0.3")
}
```

## Contributing & Releases

### Making a release
Releases are fully automated. To publish a new version:

1. Make your changes and ensure the build passes:
```sh
   ./gradlew build
   ./gradlew test
```

2. Commit with the release prefix:
```sh
   git commit -m "release: v1.2.0 — description of changes"
   git push origin main
```

3. The CI pipeline will automatically:
   - Run all tests
   - Sync build.gradle.kts version to match the commit message
   - Create a GitHub release with the version tag
   - JitPack automatically publishes from the release tag

### Version format
Follow semver: `v{major}.{minor}.{patch}`
- Patch: `v1.0.1` — bug fixes
- Minor: `v1.1.0` — new components or features  
- Major: `v2.0.0` — breaking changes

## Usage

### Apply the theme
```kotlin
import com.aks.android_ui_library.tokens.Colors
import com.aks.android_ui_library.tokens.Typography

MaterialTheme(
    colorScheme = Colors.lightScheme,
    typography = Typography.default
) {
    // your content
}
```

### Use components
```kotlin
import com.aks.android_ui_library.components.buttons.PrimaryButton
import com.aks.android_ui_library.components.inputs.TextInput
import com.aks.android_ui_library.components.feedback.LoadingView

// Button
PrimaryButton(
    text = "Get Started",
    onClick = { /* handle click */ }
)

// Text input
TextInput(
    label = "Email",
    value = email,
    onValueChange = { email = it }
)

// Loading
LoadingView(message = "Please wait...")
```

## Components
- **Buttons:** PrimaryButton, SecondaryButton, DestructiveButton, GhostButton, IconButton
- **Inputs:** TextInput (with optional character counter via `maxLength`), SecureInput
- **Feedback:** LoadingView, EmptyStateView, ToastView
- **Cards:** CardView
- **Badges:** Badge
- **Navigation:** BackButton

## Design Tokens
All components use semantic tokens from tokens/ — colors, typography, spacing, and radius.

## License
MIT
