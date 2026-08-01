# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [2.0.0] - 2026-08-01

### Changed — BREAKING

- **Repository renamed and transferred**: `android-ui-library` has moved from `github.com/aks5686/android-ui-library` to `github.com/Syzygy-Hub/syzygy-ui-android`.
  - Kotlin package changed from `com.aks.android_ui_library` to `com.syzygyhub.ui.android`.
  - Gradle module `namespace` and JitPack coordinates changed to `com.github.Syzygy-Hub:syzygy-ui-android`.
  - App theme composable renamed from `AndroiduilibraryTheme` to `SyzygyUiTheme`; Android theme resource renamed from `Theme.Androiduilibrary` to `Theme.SyzygyUi`.
  - **Consumers must update their JitPack dependency coordinates** to `com.github.Syzygy-Hub:syzygy-ui-android:<version>` and their imports to `com.syzygyhub.ui.android.*`. No other API-level renames were made to existing components.

### Added

- **Inputs**: `SearchInput` (debounced, clear button; named to avoid colliding with Material 3's `SearchBar`), `ToggleSwitch`, `CheckboxInput`, `RadioButtonInput`, `SliderInput`, `Dropdown`, `SegmentedControl`, `QuantityStepper` — the `*Input` naming avoids colliding with Material 3's own `Checkbox`/`RadioButton`/`Slider` composables.
- **Display** (new category): `Avatar`, `DividerLine` (avoids colliding with Material 3's `HorizontalDivider`), `Chip`, `ListRow`, `SectionHeader`, `LazyImageView` (async image loading with a two-tier cache — see Fixed, below — and no third-party image-loading dependency), `StarRatingView`, `CountBadge`.
- **Feedback**: `ShimmerView`, `ProgressBar`, `PullToRefresh`, `ErrorStateView` (retry pattern, mirrors `EmptyStateView`).
- **Overlay** (new category): `ModalDialog` (named to avoid colliding with `androidx.compose.ui.window.Dialog`), `BottomSheet`, `CollapsibleView`.
- **Navigation**: `TabBar` (edge-to-edge Material 3 `NavigationBar`), `BottomNavigationBar` (floating icon-only pill — a visual alternative to `TabBar`), `AppBar`, `PagerView` (wraps `HorizontalPager`).
- **Transitions** (new): `NavigationTransitions.slideTransition(_)`, `.crossFadeTransition()`, `.slideVerticalTransition(_)`, `.modalPresentationTransition()` — `ContentTransform` helpers for use with `AnimatedContent`.

### Fixed

- CI now runs instrumented tests (`connectedAndroidTest`) on a real Android Emulator, instead of only unit-testing against the JVM host.
- CI's new `lint` job fetches the shared ktlint `.editorconfig` from `syzygy-lint-config` (pinned to `v1.0.0`) and runs `ktlintCheck`, wired via the `org.jlleitschuh.gradle.ktlint` Gradle plugin.
- **`LazyImageView` rebuilt as a genuine two-tier cache**: an in-memory `androidx.collection.LruCache` (sized to 1/8th of app max memory) backed by an on-disk cache under `context.cacheDir/syzygy_image_cache/` (SHA-256-hashed filenames). Adds one retry with backoff (500ms) on transient network failures, extracted into a standalone, unit-tested `retryOnIOException` helper. Cancellation stays automatic via `LaunchedEffect(url)` — Compose cancels that coroutine when the key changes or the row leaves composition (e.g. scrolling out of a `LazyColumn`), so no manual cancellation bookkeeping is needed. HTTP cache-header revalidation (ETag/Cache-Control) is deliberately out of scope for a zero-dependency cache — documented in code comments.
- **Disk cache eviction**: the on-disk cache is now capped at 75MB with least-recently-used eviction (by file `lastModified`, touched on every cache hit so frequently redisplayed images survive eviction) — previously the cache directory grew unbounded, one file per unique image URL ever loaded.
- **Fixed `ktlintCheck` silently skipping all `.kt` source files**: this module never applies the classic Kotlin Gradle Plugin (`org.jetbrains.kotlin.android`) — only the Compose Compiler subplugin — because AGP 9.x's built-in Kotlin support conflicts with it. `ktlint-gradle`'s task generation depends on the classic plugin's `KotlinSourceSet` API, so without it, `ktlintCheck` only ever linted `.kts` build scripts, never `app/src/**/*.kt`. Added `ktlintCheckSources`/`ktlintFormatSources` tasks that invoke `ktlint-cli` directly against the real Kotlin source directories, wired into the standard `ktlintCheck`/`ktlintFormat` tasks. A module-local `app/.editorconfig` override disables `ktlint_standard_function-naming` for this module, since Compose's `@Composable` PascalCase naming convention is a legitimate, universal exception to that rule, not a style violation.

### Documentation

- Added a Syzygy banner to the top of README.md, below the badge row.
- Added a "Design Tokens" section documenting the actual `Colors`, `AppTypography`, `Spacing`, and `Radius` values.
- Reordered README.md's sections to: Requirements, Installation, Components, Design Tokens, Usage, Contributing & Releases, License.
- Fixed the Usage section's example, which previously referenced a nonexistent `Colors.lightScheme`/`Typography.default` API — it now uses the real `SyzygyUiTheme` composable.

## [1.0.4] - v1.0.4

- TextInput character counter.

## [1.0.3] - v1.0.3

- Update installation docs.

## [1.0.2] - v1.0.2

- Add JitPack publishing and usage docs.

## [1.0.1] - v1.0.1

- README improvements.

## [1.0.0] - v1.0.0

- Initial release.
