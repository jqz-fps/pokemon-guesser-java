package jqz.fps.Utilities;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManager {

    /**
     * So, with this method we can read a text file
     * and get it in a String
     * @param fileRoute is the route of the file
     * @return the content of the file
     */

    public static String read_txt_file(String fileRoute) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileRoute));
            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    /**
     * With this method we can save a text file
     * (i won't use this xd)
     * @param fileRoute is the route of the file
     * @param content the content that we want for the file
     */

    public static void save_txt_file(String fileRoute, String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileRoute, StandardCharsets.UTF_8));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
