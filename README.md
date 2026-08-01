# syzygy-ui-android

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Platform](https://img.shields.io/badge/Android-API%2029%2B-3DDC84?logo=android&logoColor=white)](https://developer.android.com)
[![JitPack](https://jitpack.io/v/Syzygy-Hub/syzygy-ui-android.svg)](https://jitpack.io/#Syzygy-Hub/syzygy-ui-android)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![CI](https://github.com/Syzygy-Hub/syzygy-ui-android/actions/workflows/android.yml/badge.svg)](https://github.com/Syzygy-Hub/syzygy-ui-android/actions/workflows/android.yml)

<picture>
  <source media="(prefers-color-scheme: dark)" srcset="https://raw.githubusercontent.com/Syzygy-Hub/syzygy-brand-assets/main/Assets/syzygy-banner-dark-2400.png">
  <img src="https://raw.githubusercontent.com/Syzygy-Hub/syzygy-brand-assets/main/Assets/syzygy-banner-light-2400.png" alt="Syzygy" width="500">
</picture>

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
    implementation("com.github.Syzygy-Hub:syzygy-ui-android:v2.0.0")
}
```

## Components

- **Buttons:** PrimaryButton, SecondaryButton, DestructiveButton, GhostButton, IconButton
- **Cards:** CardView
- **Badges:** Badge
- **Inputs:** TextInput (with optional character counter via `maxLength`), SecureInput, SearchInput (debounced, with clear button), ToggleSwitch, CheckboxInput, RadioButtonInput, SliderInput, Dropdown, SegmentedControl, QuantityStepper
- **Display:** Avatar, DividerLine, Chip, ListRow, SectionHeader, LazyImageView, StarRatingView, CountBadge
- **Feedback:** LoadingView, EmptyStateView, ToastView, ShimmerView, ProgressBar, PullToRefresh, ErrorStateView
- **Overlay:** ModalDialog, BottomSheet, CollapsibleView
- **Navigation:** BackButton, TabBar, BottomNavigationBar, AppBar, PagerView
- **Transitions:** `NavigationTransitions.slideTransition(_)`, `.crossFadeTransition()`, `.slideVerticalTransition(_)`, `.modalPresentationTransition()`

See [CHANGELOG.md](CHANGELOG.md) for version history.

## Design Tokens

All tokens live under `tokens/` and are consumed as object members — e.g. `Spacing.md`, `Radius.sm`. Color/typography tokens are layered on top of Material 3's own `ColorScheme`/`Typography` as extension properties.

### Colors (`Colors`)
Extension properties on Material 3's `ColorScheme`, so they participate in Dark Mode and dynamic color automatically. `primary`/`secondary`/`tertiary` come from the app theme's own light/dark schemes (`ui/theme/Theme.kt`); `success`/`warning`/`danger` are added by this library:

| Token | Value |
|---|---|
| `ColorScheme.success` | `#2E7D32` |
| `ColorScheme.onSuccess` | `#FFFFFF` |
| `ColorScheme.warning` | `#F9A825` |
| `ColorScheme.onWarning` | `#000000` |
| `ColorScheme.danger` | aliases Material 3's `error` |
| `ColorScheme.onDanger` | aliases Material 3's `onError` |

### Typography (`AppTypography`)
Semantic aliases on top of Material 3's `Typography` type scale:

| Token | Maps to |
|---|---|
| `display` | `displayLarge` |
| `headline` | `headlineSmall` |
| `title` | `titleMedium` |
| `body` | `bodyMedium` |
| `label` | `labelMedium` |
| `caption` | `labelSmall` |

```kotlin
Text(text = "Hello", style = MaterialTheme.typography.title)
```

### Spacing (`Spacing`)

| Token | Value |
|---|---|
| `xs` | 4.dp |
| `sm` | 8.dp |
| `md` | 16.dp |
| `lg` | 24.dp |
| `xl` | 32.dp |
| `xxl` | 48.dp |

### Corner Radius (`Radius`)

| Token | Value |
|---|---|
| `sm` | 4.dp |
| `md` | 8.dp |
| `lg` | 16.dp |
| `full` | 999.dp (pill/capsule shapes) |

## Usage

```kotlin
import com.syzygyhub.ui.android.components.buttons.PrimaryButton
import com.syzygyhub.ui.android.components.inputs.TextInput
import com.syzygyhub.ui.android.ui.theme.SyzygyUiTheme

SyzygyUiTheme {
    PrimaryButton(text = "Get Started", onClick = { /* handle click */ })
    TextInput(label = "Email", value = email, onValueChange = { email = it })
}
```

See the [Components](#components) list above for everything else available.

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

## License
MIT
