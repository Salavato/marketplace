plugins {
    id("build-jvm")
    `maven-publish`
}

val zipSpecs by tasks.registering(Zip::class) {
    description = "Упаковка спецификаций в Zip-архив"
    from("specs")
    archiveClassifier.set("spec")
    archiveExtension.set("zip")
}

val specsElements by configurations.creating {
    isCanBeResolved = false
    isCanBeConsumed = true
    outgoing.artifact(zipSpecs)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            artifact(zipSpecs) {
                classifier = "spec"
                extension = "zip"
            }
        }
    }
}
