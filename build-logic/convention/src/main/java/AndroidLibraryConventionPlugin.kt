import com.android.build.gradle.LibraryExtension
import com.perullheim.convention.androidTestImplementation
import com.perullheim.convention.configureKotlinAndroid
import com.perullheim.convention.libs
import com.perullheim.convention.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

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