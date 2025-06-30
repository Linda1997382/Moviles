pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()  
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

    // ¡ESTA ES LA ÚNICA CONFIGURACIÓN DEL CATÁLOGO QUE DEBE EXISTIR!
    //versionCatalogs {
        //create("libs") {
      //      from(files("gradle/libs.versions.toml"))
    //    }
  //  }
//}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "GestordeHerramientas"
include(":app") }