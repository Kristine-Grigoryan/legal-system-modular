# POM Fixes Summary

## Root `pom.xml`
- **Fixed** `mapstruct.version` from `1.6.0.Final` (non-existent) to `1.6.3`

## `persistence/pom.xml`
- **Removed** redundant `<groupId>` (inherited from parent)
- **Removed** redundant `<properties>` block (`maven.compiler.source/target` hardcoded to 21), replaced with `${java.version}` reference
- **Added** `<optional>true</optional>` to Lombok dependency
- **Added** `maven-compiler-plugin` with Lombok annotation processor path (using `${lombok.version}`)

## `common/pom.xml`
- **Removed** redundant `<groupId>` and explicit `<version>` (now inherits `1.0-SNAPSHOT` from parent)
- **Removed** redundant `<properties>` block, replaced with `${java.version}` references
- **Fixed** `spring-boot-starter-mail` — removed invalid `<version>4.0.5</version>` (no such version; now uses BOM-managed version)
- **Fixed** `spring-boot-starter-thymeleaf` — removed invalid `<version>4.0.2</version>` (same reason)
- **Removed** redundant standalone `spring-web` dependency
- **Added** `spring-boot-starter-web` dependency (needed for `MultipartFile` in service interfaces)
- **Removed** `<scope>compile</scope>` from MapStruct dependency (compile is the default)
- **Added** `jspecify` 1.0.0 dependency (required by Spring Data JPA `Specification` interface)
- **Added** `maven-compiler-plugin` with Lombok + MapStruct annotation processor paths
- **Standardized** all version references to use `${mapstruct.version}` and `${lombok.version}` from parent

## `app/pom.xml`
- **Added** missing Lombok dependency (was missing entirely, causing all `@Slf4j`, `@RequiredArgsConstructor`, `@Getter` errors)
- **Added** missing `commons-io` dependency (used in `MainController`)
- **Added** `<version>${spring.boot.version}</version>` to `spring-boot-maven-plugin`
- **Added** `<version>3.13.0</version>` to `maven-compiler-plugin` (was producing a warning)

## `rest/pom.xml`
- **Removed** redundant `<groupId>`, `<version>`, empty `<url/>`, `<licenses>`, `<developers>`, `<scm>` tags
- **Removed** redundant `<properties>` block (java.version=17 was inconsistent with parent's 21)
- **Removed** duplicate `jjwt-api` dependency
- **Replaced** non-existent `spring-boot-starter-data-jpa-test` with `spring-boot-starter-test`
- **Replaced** non-existent `spring-boot-starter-security-test` with `spring-security-test`
- **Fixed** missing `<version>` on Lombok in `annotationProcessorPaths` (would cause build failure)
- **Added** `<version>${spring.boot.version}</version>` to `spring-boot-maven-plugin`
- **Added** `<version>3.13.0</version>` to `maven-compiler-plugin`
- **Standardized** all version references to use `${lombok.version}` and `${mapstruct.version}`

## Java Source Fixes
- **`AppApplication.java`** — Fixed import `org.springframework.boot.persistence.autoconfigure.EntityScan` → `org.springframework.boot.autoconfigure.domain.EntityScan`
- **`RestApplication.java`** — Same `@EntityScan` import fix
- **`WebSecurityConfig.java`** (app) — Fixed `DaoAuthenticationProvider` constructor: use no-arg constructor + `setUserDetailsService()`
- **`RestSecurityConfig.java`** (rest) — Same `DaoAuthenticationProvider` fix + added `throws Exception` to `securityFilterChain` method
- **`LegalCaseSpecification.java`** — Fixed import `org.jspecify.annotations.Nullable` → `org.springframework.lang.Nullable`

## Maven Settings Fix
- **`~/.m2/settings.xml`** — Removed duplicate `<server>` entry with id `mla-app-ui`

