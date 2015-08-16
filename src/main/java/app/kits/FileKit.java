package app.kits;

import com.google.common.io.Files;
import com.jfinal.kit.PathKit;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * .
 * </p>
 *
 * @author Jerry Ou
 * @version 1.0 2014-02-12 13:41
 * @since JDK 1.6
 */
public class FileKit {

    public static File getFile(String path) throws IOException {
        File file = new File(PathKit.getWebRootPath() + path);
        if (!file.exists()) {
            Files.createParentDirs(file);
        }
        return file;
    }

}
