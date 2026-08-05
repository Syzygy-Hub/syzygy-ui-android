# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [2.4.0] - 2026-08-05

### Added
- SyzygyTheme, SyzygyThemeProvider, LocalSyzygyTheme CompositionLocal, runtime theme switching
- 3 built-in themes: default, dark, highContrast
- Component-level theme override parameter on all @Composable components

### Changed
- All components now read visual tokens from LocalSyzygyTheme.current

### Fixed
- CI release.yml sed pattern: versionName → version to match build.gradle.kts field name
- Accessibility: ButtonGroup, ToggleSwitch, CheckboxInput, RadioButtonInput, DatePickerField, TimePicker

## [2.3.0] - 2026-08-05

### Changed (Breaking)
- **Color token vocabulary alignment** — `Colors` extension object updated to canonical token set:
  - `danger` / `onDanger` removed; use `destructive` / `onDestructive` instead (delegates to M3's `error` / `onError`)
  - `ConfirmDialog`, `NetworkStatusBanner` updated to use new names
- **New tokens added**: `primarySubtle`, `errorMuted`, `textDisabled`, `textInverse` (all derived from existing M3 `ColorScheme` values; no hard-coded hex)

### Fixed
- CI `release.yml`: extraction regex now strips optional leading `v` (`v?`) so bare-version commit messages (`release: 2.3.0`) work correctly; removed redundant `COMMIT_VERSION="${COMMIT_VERSION#v}"` in Sync step

## [2.2.1] - 2026-08-04

### Added
- `ColorSwatch`: `size: Dp = 32.dp` parameter lets callers override the swatch diameter/side length
- `CheckboxInput`, `RadioButtonInput`, `ToggleSwitch`: `stateDescription` in the `semantics` block announces checked/selected/on state to TalkBack (Android 12+)
- `ComposeComponentSmokeTest`: 10 instrumented smoke tests covering `PrimaryButton`, `TextInput`, `ConfirmDialog`, `ModalDialog`, `BottomSheet`, `NetworkStatusBanner`, `Accordion`, `PhoneInput`, `SearchableDropdown`, `StepIndicator` (8 → 18 instrumented tests)

### Fixed
- `Radius.full`: corrected from `999.dp` to `9999.dp` to match cross-platform token spec
- `ColorSwatch`: `SwatchSize` corrected from `40.dp` to `32.dp` to match design spec
- README: removed erroneous extra "v" prefix from version badge (`Version-v2.2.0` → `Version-2.2.0`)

## [2.2.0] - 2026-08-04

### Added

- **Display**: `PageControl` (aka DotIndicator; read-only row of dots syncing with `PagerView` — no tap-to-navigate, matching a page indicator's usual role as a passive status readout), `Accordion` (managed group of `CollapsibleView`-style expandable sections with coordinated open/closed state; single-open-at-a-time by default via `allowsMultipleOpen`), `Timeline` (aka ActivityFeed; vertical event list with a dot/icon connecting line, via fixed-height spacer segments rather than a measured `Canvas` path, plus a `TimelineItemAlignment` enum for leading/trailing layouts), `ColorSwatch` (circle/square color preview with optional label and a `focus`/`primary`-colored selection border).
- **Inputs**: `SearchableDropdown` (its own `ExposedDropdownMenuBox` composition rather than wrapping `Dropdown` directly, since `Dropdown`'s text field is `readOnly` while this one must stay editable to drive the search query), `PhoneInput` (tappable flag + dial-code prefix selector, numeric keyboard, exposes both a formatted display string and a digits-only raw number; ships with a 15-entry real, hardcoded starter country list, injectable via `countries` so consumers can override without forking), `CurrencyInput` (locale-aware formatting via JDK-native `java.text.NumberFormat`/`DecimalFormat`, defaulting to `Locale.getDefault()` but overridable via a `locale` param; exposes the raw `Double` separately from the formatted display string).
- **Feedback**: `NetworkStatusBanner` (top/bottom-anchored "No internet connection" banner, driven by `ConnectivityManager.NetworkCallback` wrapped in a `produceState`-based Compose state holder; supports a `manualOverride` to force-show/hide), `ConfirmDialog` (preset confirm/cancel modal built on `ModalDialog`, with an `isDestructive` flag styling the confirm button in the `danger` color token).
- **Layout**: `SafeAreaWrapper` (applies `WindowInsets.safeDrawing` via `Modifier.windowInsetsPadding(...)`, with a configurable `edges` set so consumers opt into only the insets they need — genuinely load-bearing on Android, unlike iOS where safe-area handling is largely automatic), `LabeledDivider` (`DividerLine` broken by a centered/leading/trailing text label, via a `Row` of two weighted `DividerLine` segments flanking the label).

No CI/lint carry-over fixes were needed for this release — Android's ktlint setup was already fully consistent between local and CI.

## [2.1.0] - 2026-08-03

### Added

- **Buttons**: `LoadingButton` (built-in loading spinner, disabled while loading), `AppFloatingActionButton` (named to avoid colliding with Material 3's own `FloatingActionButton`), `ButtonGroup` (segmented row, single- or multi-select via `multiSelect`).
- **Inputs**: `TextArea` (multi-line, native `minLines`/`maxLines`), `OTPInput` (fixed-length auto-advancing code entry), `TagInput` (dismissible `Chip`-backed tag entry), `DatePickerField`/`TimePickerField` (wrap Material 3's `DatePicker`/`TimePicker` in a dialog, showing formatted text when closed), `FormField` (generic label/content/error/helper wrapper), `PasswordStrengthIndicator` (real length + character-class heuristic, not hardcoded).
- **Display**: `AvatarGroup` (overlapping `Avatar` stack with "+N" overflow), `StatsCard` (aka MetricCard; label/value/trend), `RatingInput` (dedicated interactive counterpart to the read-only-by-default `StarRatingView`).
- **Feedback**: `SkeletonView` (shape/size-parameterized shimmer placeholder, mirrors `ShimmerView`'s animation), `CircularProgress` (determinate + indeterminate), `InlineAlert` (aka Banner; 4 variants using the new `*Muted` color tokens), `AppSnackbar` (named to avoid colliding with Material 3's `Snackbar`; standalone presentational composable with its own `LaunchedEffect`-driven auto-dismiss, consistent with this library's no-hidden-global-state pattern).
- **Overlay**: `ActionSheet` (bottom-anchored labelled actions, follows `BottomSheet`'s `ModalBottomSheet` presentation convention), `Popover` (anchored floating content via `Popup`), `Tooltip` (wraps Material 3's `TooltipBox`/`PlainTooltip`).
- **Navigation**: `SideMenu` (aka Drawer; wraps `ModalNavigationDrawer` behind a simple `isOpen`/`onClose` boolean rather than exposing `DrawerState` directly), `FloatingTabBar` (floating pill bar with icon **and** label per item — the remaining cell in the {edge-to-edge vs floating} x {icon-only vs icon+label} matrix, distinct from the floating, icon-only `BottomNavigationBar`), `StepIndicator` (aka WizardSteps; active/completed/pending step progress), `Breadcrumbs` (tappable navigation trail using the new `separator` color token).
- **Layout**: `AdaptiveStack` (`Row` above a width breakpoint, `Column` below, via `BoxWithConstraints`), `FlowLayout` (wraps Compose Foundation's native `FlowRow`), `StickyHeader` (wraps `LazyColumn`'s native `stickyHeader { }`).
- **Transitions**: `NavigationTransitions.scaleTransition()`, `.fadeThroughTransition()` (sequential fade-out-then-fade-in, not a simultaneous cross-fade).
- **Design tokens**: new `Colors` entries `primaryMuted`, `destructiveMuted`, `successMuted`, `warningMuted`, `surfaceSecondary`, `surfaceTertiary`, `textTertiary`, `overlay`, `link`, `focus`, `separator`; new `AppTypography.largeTitle`; new `Spacing.xxs`/`Spacing.xxxl`; new `Radius.xs`/`Radius.xl`; new token files `Elevation`, `Opacity`, `BorderWidth`, `IconSize`, `Animation` (`Duration` + `Easing`, with `spring()` kept as a factory function rather than a plain `Easing` since Compose's `spring()` is its own `AnimationSpec` type).

### Fixed

- **`PagerView` moved from the Navigation category to Layout**: it's presentational paged content (already documented in its own doc comment as distinct from `TabBar`'s navigation chrome), not navigation chrome, and this repo already has a `layout/` component directory. This release also adds the `Layout:` bullet line to README's Components section for the first time — the category has existed in code since `KeyboardAvoidingScrollView` was added, but the README bullet was never created for it.

### Changed

- README's Design Tokens section condensed from per-token prose into compact `| Token | Value |` reference tables, applied uniformly across both existing and newly-added token categories.

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
