package uz.pdp.anicinema.utils.contant;

import java.io.File;
import java.nio.file.Path;

public class AppConstant {

    public static final Path BASE_ATTACHMENT_PATH = Path.of(System.getProperty("user.home")+ File.separator + "ani_cinema"+ File.separator + "attachments" + File.separator);
    public static final String ATTACHMENT_URL = "http://localhost:8080/api/v1/attachment/download/";

    public static final String API_V1 = "/api/v1";
    public static final String MOVIE = "/movie";
    public static final String ATTACHMENT = "/attachment";
    public static final String AUTH = "/auth";
    public static final String COMMENT = "/comment";
    public static final String SHORTS = "/shorts";

}
