plugins {
    id("build-jvm")
    alias(libs.plugins.openapi.generator)
}

val specsFromLib by configurations.creating {
    isCanBeResolved = true
    isCanBeConsumed = false
}

dependencies {
    specsFromLib(project(":marketplace-specs", "specsElements"))
    implementation(kotlin("stdlib"))
    implementation(libs.jackson.kotlin)
    implementation(libs.jackson.datatype)
    testImplementation(kotlin("test-junit"))
}

val specDir = layout.buildDirectory.dir("specs")

tasks {
    val extractLibSpecs by registering(Copy::class) {
        description = "Извлекаем содержимое архива во временную директорию"
        dependsOn(specsFromLib)
        from(specsFromLib.elements.map { it.map { file -> zipTree(file) } })
        into(specDir)
    }

    val openApiGenerate by getting {
        dependsOn(extractLibSpecs)
    }

    compileKotlin {
        dependsOn(openApiGenerate)
    }
}

openApiGenerate {
    val openapiGroup = "${rootProject.group}.api.v1"
    generatorName.set("kotlin")
    packageName.set(openapiGroup)
    apiPackage.set("$openapiGroup.api")
    modelPackage.set("$openapiGroup.models")
    invokerPackage.set("$openapiGroup.invoker")
    inputSpec.set(specDir.map { it.file("specs-v1.yaml").asFile.absolutePath })
    globalProperties.apply {
        put("models", "")
        put("modelDocs", "false")
    }
    configOptions.set(
        mapOf(
            "dateLibrary" to "string",
            "enumPropertyNaming" to "UPPERCASE",
            "serializationLibrary" to "jackson",
            "collectionType" to "list"
        )
    )
}

sourceSets {
    main {
        java.srcDir(layout.buildDirectory.dir("generate-resources/main/src/main/kotlin"))
    }
}
