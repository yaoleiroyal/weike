package app.kits;

import com.github.sog.config.JFinalApp;
import com.github.sog.plugin.monogodb.MongoKit;
import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.commons.io.FilenameUtils;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * .
 * </p>
 *
 * @author sagyf yang
 * @version 1.0 2014-05-21 11:42
 * @since JDK 1.6
 */
public class GridFsKit {
    public static GridFSDBFile getFile(String id) {
        return getGridFS().findOne(new ObjectId(id));
    }
    public static GridFSDBFile getFileWithName(String name) {
        return getGridFS().findOne(name);
    }

    public static List<GridFSDBFile> getFiles() {
        return getGridFS().find(new BasicDBObject());
    }

    public static void storeFile(String title, File image)
            throws IOException {
        GridFS fs = getGridFS();
        fs.remove(image.getName()); // delete the old file
        GridFSInputFile gridFile = fs.createFile(image);
        gridFile.save();
        gridFile.setContentType("image/" + FilenameUtils.getExtension(image.getName()));
        gridFile.setFilename(image.getName());
        gridFile.put("title", title);
        gridFile.save();
    }

    public static GridFS getGridFS() {
        String collection = JFinalApp.configuration.getProperty("morphia.db.collection.upload", "uploads");
        return new GridFS(MongoKit.getDB(), collection);
    }
}
