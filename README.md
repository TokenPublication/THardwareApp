## THardware

# Version 1.0.1 Notes

Added get fiscal busy

# Version 1.0.0 Notes

Added SPI Socket Connector.

## THardware330TR

# Version 1.0.0 Notes

Added THardware330TR module.

## Getting Started

1) To access the THardware lib, modify the $ROOT_PROJECT/build.gradle file
   (or possibly any config file that specifies repositories used in the project) as follows
   (Make sure you add the Token Repository before other repositories):

```groovy
repositories {
    maven {
        name = "GithubPackages"

        url = uri("https://maven.pkg.github.com/TokenPublication/THardwareApp")

        credentials {
            username = "TokenPublication"
            password = "INSERT_TOKEN_PUBLICATION_CONSUME_TOKEN_HERE"
        }
    }
    
    mavenCentral()
    
}
```

2) To use THardware lib in the app module, add the following dependency to app/build.gradle file:

# THardware lib(TR1000):
```groovy
dependencies {
    
    implementation 'com.tokeninc:thardware:1.0.1'
    
}
```

# THardware330tr lib(TR330):
```groovy
dependencies {
    
    implementation 'com.tokeninc:thardware.tr330:1.0.0'
    
}
```

## Documentation & Explanation

# THardware lib(TR1000):
Please refer to the HTML Java Documentation file located under javadoc/index.html\
Please refer to MainActivity for example.

# THardware330tr lib(TR330):
Please refer to the HTML Java Documentation file located under javadoc-330tr/index.html\
Please refer to MainActivity330tr for example.
