package uz.pdp.anicinema.utils.contant;

import java.io.File;
import java.nio.file.Path;

public class AppConstant {

    public static Path BASE_ATTACHMENT_PATH = Path.of(System.getProperty("user.home")+ File.separator + "ani_cinema"+ File.separator + "attachments" + File.separator);
    public static String BASE_URL = "http://localhost:8080/";
}
