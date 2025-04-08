package com.perullheim.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun DependencyHandlerScope.implementation(dependency: Any) {
    "implementation"(dependency)
}

fun DependencyHandlerScope.kapt(dependency: Any) {
    "kapt"(dependency)
}

fun DependencyHandlerScope.ksp(dependency: Any) {
    "ksp"(dependency)
}

fun DependencyHandlerScope.testImplementation(dependency: Any) {
    "testImplementation"(dependency)
}

fun DependencyHandlerScope.androidTestImplementation(dependency: Any) {
    "androidTestImplementation"(dependency)
}