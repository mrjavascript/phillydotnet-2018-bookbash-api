package org.melusky.bookbash.utility.system;

import org.springframework.stereotype.Component;

@Component
public class FileSystemHelper {

    private String osName;
    private Boolean isWindows;

    public FileSystemHelper() {
        osName = System.getProperty("os.name");
        isWindows = (osName != null && osName.startsWith("Windows"));
    }

    public String getFilesystemRoot(String path) {
        if (path == null) {
            throw new IllegalStateException("Path cannot be null!");
        }

        if (! isWindows && path.length() > 2) {

            //
            //  If a windows property was passed in and the host system is different
            //  handle case differently for absolute paths
            if (path.toUpperCase().charAt(0) >= 'A'
                    && path.toUpperCase().charAt(0) <= 'Z'
                    && path.charAt(1) == ':') {

                //
                //  remove absolute path
                path = path.substring(2);

                //
                //  prepend the home directory to the path
                return System.getProperty("user.home") + "/" + path;
            }
        }

        return path;
    }

}
