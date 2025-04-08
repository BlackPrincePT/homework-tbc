import com.android.build.gradle.LibraryExtension
import com.pegio.convention.androidTestImplementation
import com.pegio.convention.configureKotlinAndroid
import com.pegio.convention.implementation
import com.pegio.convention.libs
import com.pegio.convention.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35
            }

            dependencies {
                testImplementation(libs.findLibrary("kotlin.test").get())
                androidTestImplementation(libs.findLibrary("kotlin.test").get())
            }
        }
    }
}