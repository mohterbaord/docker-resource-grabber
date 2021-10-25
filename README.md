# Docker Resource Grabber

The plugin adds a task `grabDockerResources` to the `Build` group that copies files from Docker containers to the project.
It may be useful for that libraries that are distributed by Docker images and should be added to the project as well.

## Configuration

```kotlin
dockerResourceGrabber {
    fromImage("<image-vendor-1>/<image-name-1>:<version>") {
        grab("/path/to/docker-resource-1.jar", "<destDir>")
        grab("/path/to/docker-resource-2.jar", "<destDir>")
        // ...
    }
    fromImage("<image-vendor-2>/<image-name-2>:<version>") {
        grab("/path/to/docker-resource-1.jar", "<destDir>")
        grab("/path/to/docker-resource-2.jar", "<destDir>")
        // ...
    }
    // ...
}
```
