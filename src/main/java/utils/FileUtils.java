package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {
    public static final Path SCREENSHOTS_DIR_PATH = Paths.get("target/results/screenshots").toAbsolutePath();
    public static void copy(Path original, Path target) {
        try {
            Files.copy(original, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ignored) {
        }
    }

    public static boolean cleanScreenshots() {
        if (!isScreenshotsDirNotEmpty()) return true;
        File screenshotsDir = SCREENSHOTS_DIR_PATH.toFile();
        boolean result = true;
        for (File file : screenshotsDir.listFiles()) {
            result &= file.delete();
        }
        return result;
    }

    public static boolean isScreenshotsDirNotEmpty() {
        File dir = SCREENSHOTS_DIR_PATH.toFile();
        return dir.listFiles() != null && dir.listFiles().length > 0;
    }
}
