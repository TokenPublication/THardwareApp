## THardware App

# Version 1 Notes

Added SPI Socket Connector

## Getting Started

1) To use THardware wrapper modify the $ROOT_PROJECT/build.gradle file
   (or possibly any config file that specifies repositories used in the project) as follows
   (Make sure you add the Token Repository before other repositories):

```groovy
repositories {
    maven {
        name = "GithubPackages"

        url = uri("https://maven.pkg.github.com/TokenPublicRepo/THardware")

        credentials {
            username = "TokenPublicRepo"
            password = "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"
        }
    }
    
    mavenCentral()
    ...
}
```

2) To use THardware wrapper modify the app/build.gradle file as follows:

```groovy
dependencies {
    
    implementation 'com.tokeninc:thardware:1.0.0'
    
}
```
