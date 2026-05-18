plugins {
    id("build-jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":marketplace-api-v1-jackson"))
    implementation(project(":marketplace-common"))
    testImplementation(kotlin("test-junit"))
}
