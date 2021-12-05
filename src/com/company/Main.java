package prak_23_24;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception
    {
        String adress = "https://www.mirea.ru";
        Document doc = Jsoup.connect(adress).get();
        Elements img = doc.getElementsByTag("img");

        for (Element el : img) {


            String[] filenames = el.attr("src").split("/");
            String filename = filenames[filenames.length-1];
            String[] file_extensions = filename.split("\\.");

            String file_extension = file_extensions[file_extensions.length-1];

            try {
                URL url = new URL(adress + el.attr("src"));

                BufferedImage image = ImageIO.read(url);
                File file = new File("/home/pasta/IdeaProjects/Prak_23_24/images/" + filename);


                if (image != null && file != null) {
                    ImageIO.write(image, file_extension, file);
                    System.out.println(filename);
                }
            }
            catch (IOException e){}

        }
    }
}
