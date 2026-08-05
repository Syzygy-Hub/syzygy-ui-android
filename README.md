# syzygy-ui-android

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Platform](https://img.shields.io/badge/Android-API%2029%2B-3DDC84?logo=android&logoColor=white)](https://developer.android.com)
[![Version](https://img.shields.io/badge/Version-2.4.0-blue.svg)](CHANGELOG.md)
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
    implementation("com.github.Syzygy-Hub:syzygy-ui-android:v2.2.0")
}
```

## Components

79 components across 9 categories (counted individually below):

- **Buttons:** PrimaryButton, SecondaryButton, DestructiveButton, GhostButton, IconButton, LoadingButton, AppFloatingActionButton, ButtonGroup
- **Cards:** CardView
- **Badges:** Badge
- **Inputs:** TextInput (with optional character counter via `maxLength`), SecureInput, SearchInput (debounced, with clear button), ToggleSwitch, CheckboxInput, RadioButtonInput, SliderInput, Dropdown, SegmentedControl, QuantityStepper, TextArea, OTPInput, TagInput, DatePickerField, TimePickerField, FormField, PasswordStrengthIndicator, SearchableDropdown (inline search/filter over `Dropdown`'s options), PhoneInput (country-code prefix selector + numeric keyboard), CurrencyInput (locale-formatted, via JDK `NumberFormat`)
- **Display:** Avatar, DividerLine, Chip, ListRow, SectionHeader, LazyImageView, StarRatingView, CountBadge, AvatarGroup, StatsCard (aka MetricCard), RatingInput, PageControl (aka DotIndicator; syncs with `PagerView`), Accordion (managed group of `CollapsibleView`-style sections), Timeline (aka ActivityFeed), ColorSwatch
- **Feedback:** LoadingView, EmptyStateView, ToastView, ShimmerView, ProgressBar, PullToRefresh, ErrorStateView, SkeletonView, CircularProgress, InlineAlert (aka Banner), AppSnackbar, NetworkStatusBanner (`ConnectivityManager`-driven, auto-dismissing), ConfirmDialog (preset confirm/cancel `ModalDialog`)
- **Overlay:** ModalDialog, BottomSheet, CollapsibleView, ActionSheet, Popover, Tooltip
- **Navigation:** BackButton, TabBar, BottomNavigationBar, AppBar, SideMenu (aka Drawer), FloatingTabBar, StepIndicator (aka WizardSteps), Breadcrumbs
- **Layout:** KeyboardAvoidingScrollView, PagerView (presentational paged content — distinct from navigation chrome like TabBar/BottomNavigationBar), AdaptiveStack, FlowLayout, StickyHeader, SafeAreaWrapper (native `WindowInsets.safeDrawing` handling), LabeledDivider (`DividerLine` with a centered/leading/trailing text label)
- **Transitions:** `NavigationTransitions.slideTransition(_)`, `.crossFadeTransition()`, `.slideVerticalTransition(_)`, `.modalPresentationTransition()`, `.scaleTransition()`, `.fadeThroughTransition()`

**NetworkStatusBanner — cross-platform note**: On iOS and Android, `NetworkStatusBanner` self-detects connectivity via first-party OS APIs (`NWPathMonitor` / `ConnectivityManager`) and requires no `isOffline` prop. On React Native and Flutter, real network detection requires a third-party package that this library deliberately does not bundle, so the banner is controlled/presentational — pass `isOffline` from your own network state.

See [CHANGELOG.md](CHANGELOG.md) for version history.

## Design Tokens

All tokens live under `tokens/` and are consumed as object members — e.g. `Spacing.md`, `Radius.sm`. Color/typography tokens are layered on top of Material 3's own `ColorScheme`/`Typography` as extension properties.

### Colors (`Colors`)
Extension properties on Material 3's `ColorScheme`. `primary`/`secondary`/`tertiary` come from the app theme's own light/dark schemes (`ui/theme/Theme.kt`).

| Token | Value |
|---|---|
| `success` | `#2E7D32` |
| `onSuccess` | `#FFFFFF` |
| `warning` | `#F9A825` |
| `onWarning` | `#000000` |
| `danger` | aliases `error` |
| `onDanger` | aliases `onError` |
| `primaryMuted` | `primary` @ 12% over `surface` |
| `destructiveMuted` | `error` @ 12% over `surface` |
| `successMuted` | `success` @ 12% over `surface` |
| `warningMuted` | `warning` @ 12% over `surface` |
| `surfaceSecondary` | aliases `surfaceVariant` |
| `surfaceTertiary` | `surfaceVariant` @ 60% over `surface` |
| `textTertiary` | `onSurfaceVariant` @ 70% over `surface` |
| `overlay` | `scrim` @ 32% (modal/sheet/popover scrim) |
| `link` | aliases `primary` |
| `focus` | aliases `primary` |
| `separator` | aliases `outlineVariant` |

### Typography (`AppTypography`)

| Token | Maps to |
|---|---|
| `display` | `displayLarge` |
| `headline` | `headlineSmall` |
| `title` | `titleMedium` |
| `body` | `bodyMedium` |
| `label` | `labelMedium` |
| `caption` | `labelSmall` |
| `largeTitle` | `displayLarge` at 34sp, bold |

```kotlin
Text(text = "Hello", style = MaterialTheme.typography.title)
```

### Spacing (`Spacing`)

| Token | Value |
|---|---|
| `xxs` | 2.dp |
| `xs` | 4.dp |
| `sm` | 8.dp |
| `md` | 16.dp |
| `lg` | 24.dp |
| `xl` | 32.dp |
| `xxl` | 48.dp |
| `xxxl` | 64.dp |

### Corner Radius (`Radius`)

| Token | Value |
|---|---|
| `xs` | 2.dp |
| `sm` | 4.dp |
| `md` | 8.dp |
| `lg` | 16.dp |
| `xl` | 16.dp |
| `full` | 999.dp (pill/capsule shapes) |

### Elevation (`Elevation`)

| Token | Value |
|---|---|
| `none` | 0.dp |
| `sm` | 1.dp |
| `md` | 4.dp |
| `lg` | 8.dp |

### Opacity (`Opacity`)

| Token | Value |
|---|---|
| `disabled` | 0.38f |
| `secondary` | 0.60f |
| `overlay` | 0.54f |

### Border Width (`BorderWidth`)

| Token | Value |
|---|---|
| `thin` | 0.5.dp |
| `regular` | 1.dp |
| `thick` | 2.dp |

### Icon Size (`IconSize`)

| Token | Value |
|---|---|
| `sm` | 16.dp |
| `md` | 20.dp |
| `lg` | 24.dp |
| `xl` | 32.dp |

### Animation (`Animation`)

| Duration | Value |
|---|---|
| `fast` | 150ms |
| `normal` | 300ms |
| `slow` | 500ms |

| Easing | Maps to |
|---|---|
| `standard` | `FastOutSlowInEasing` |
| `decelerate` | `LinearOutSlowInEasing` |
| `accelerate` | `FastOutLinearInEasing` |
| `spring()` | `spring()` `AnimationSpec` factory (not an `Easing` — springs are physically modeled) |

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

## Theming

syzygy-ui-android v2.4.0 ships a runtime theme system built on Compose's `CompositionLocal`.

### Providing a theme

Wrap your UI in `SyzygyThemeProvider` to inject a `SyzygyTheme` into the composition:

```kotlin
import com.syzygyhub.ui.android.theme.SyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyThemeProvider

SyzygyThemeProvider(theme = SyzygyTheme.dark) {
    PrimaryButton(text = "Hello", onClick = {})
}
```

### Reading the theme

Inside any composable, read the current theme via the `LocalSyzygyTheme` composition local or the convenience function:

```kotlin
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.syzygyTheme

// Option A — direct access
val theme = LocalSyzygyTheme.current

// Option B — convenience accessor
val theme = syzygyTheme()
```

### Built-in themes

| Name | Description |
|------|-------------|
| `SyzygyTheme.default` | Light theme, standard radius and typography |
| `SyzygyTheme.dark` | Dark color palette, same radius and typography as default |
| `SyzygyTheme.highContrast` | High-contrast colors, sharp corners, heavier font weights |

### Component-level override

Every public `@Composable` in this library accepts an optional `theme: SyzygyTheme? = null` parameter. Pass a theme to override just that component without affecting the rest of the tree:

```kotlin
PrimaryButton(
    text = "Destructive action",
    onClick = {},
    theme = SyzygyTheme.highContrast,
)
```

When `theme` is `null` (the default), the component reads from `LocalSyzygyTheme.current`.

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
