package com.referencee.api.util;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.auth.FirebaseAuth;
import com.referencee.api.util.exception.UtilException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class encapsulates all the stuff required to upload
 * images into Google Cloud Storage.
 */
public class StorageUtil {
    private static final class SingletonHolder {
        private static final StorageUtil instance = new StorageUtil();
    }

    private FirebaseAuth firebaseAuth;
    private Logger logger = LogManager.getLogger(getClass());
    private Storage storage;
    private final String BUCKET = "referencee-274418.appspot.com";
    private final String SA_PATH = "referencee-sa.json";

    private StorageUtil() {
        try (InputStream stream = Objects.requireNonNull(
                StorageUtil.class.getClassLoader().getResourceAsStream(SA_PATH))) {
            StorageOptions options = StorageOptions.newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(stream))
                    .build();
            storage = options.getService();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    public static StorageUtil getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * Uploads a file to Google Cloud Storage and returns
     * {@link FilePath} object containing URL and gcsPath
     * of newly created file.
     *
     * @param file file to upload.
     * @return url and gcsPath
     * @throws UtilException if upload failed.
     */
    public FilePath uploadFile(MultipartFile file) throws UtilException {
        // Generate unique name
        String blobName = generateUniqueName(file.getOriginalFilename());
        // Create Access-control list
        List<Acl> aclList = new ArrayList<>();
        aclList.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        // Create BlobInfo with blob name and ACL specified
        BlobInfo blobInfo = BlobInfo.newBuilder(BUCKET, blobName).setAcl(aclList).build();
        // Upload file to Google Cloud Storage
        Blob blob;
        try {
            blob = storage.create(blobInfo, file.getBytes());
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new UtilException(e.getMessage());
        }
        // Retrieve URL and Google Cloud Storage path
        String url = blob.getMediaLink();
        String gcsPath = String.format("gs://%s/%s", BUCKET, blob.getName());
        return new FilePath(url, gcsPath);
    }

    /**
     * Generates unique file name based on original file name
     * and current date/time.
     *
     * @param name original file name.
     * @return new file name.
     */
    private String generateUniqueName(String name) {
        String pattern = "dd-MM-YYYY-HH-mm-ss-SSS-";
        String dateString = DateTime.now(DateTimeZone.UTC).toString(DateTimeFormat.forPattern(pattern));
        return dateString + name;
    }

    /**
     * FilePath class combines two path types used in Google Cloud Storage
     * to identify files:
     *  - URL (for global use)
     *  - gcsPath (for internal use, for example, Vision API uses gcsPath)
     */
    public class FilePath {
        private String url;
        private String gcsPath;

        public FilePath(String url, String gcsPath) {
            this.url = url;
            this.gcsPath = gcsPath;
        }

        public String getUrl() {
            return url;
        }

        public String getGcsPath() {
            return gcsPath;
        }
    }
}
