# ShogiCraft
minecraft plugin that's supposed to put shogi into minecraft

NOTE: i only intend on supporting the latest version of minecraft. if this does not fly with you, compile it for a different version yourself.

## Structure
- `./shogi` - contains the resource pack elements required for the thing to work
- `./src` - contains all the code

## Building from source
#### This is meant to be a beginner-friendly guide to building this plugin from source.
What do you need? 
- [Java 17](https://adoptium.net/?variant=openjdk17&jvmVariant=hotspot)
- [Gradle 7.3 (or higher)](https://gradle.org)
- [Git](https://git-scm.com)

Clone the repository.

Copy the shogi folder into the resource pack folder in your minecraft instance. (Or host it somehwere, if you intend to use this in your server.)

Open a command line in the cloned directory and type `gradle shadowJar`.

After a few seconds, it should be done. The finished plugin will be in `/build/libs/`.