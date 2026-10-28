// gradle.properties
val platforms: String by extra

architectury {
    common(platforms.split(","))
}

loom {
    accessWidenerPath.set(file("src/main/resources/betteradvancements.accesswidener"))
}

dependencies {
    compileOnly("net.fabricmc:sponge-mixin:0.17.4+mixin.0.8.7")
    compileOnly("io.github.llamalad7:mixinextras-common:0.5.5")
    annotationProcessor("io.github.llamalad7:mixinextras-common:0.5.5")

    api(project(":CommonApi"))
}