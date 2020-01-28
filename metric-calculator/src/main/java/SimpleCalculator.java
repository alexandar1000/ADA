import extractor.SimpleParser;

import java.io.FileNotFoundException;

public class SimpleCalculator {

    static String SRC_DIRECTORY_PATH="source_to_parse/abc";
    static String SRC_FILE_PATH = SRC_DIRECTORY_PATH + "/ServiceCentre.java";

    public static void main(String[] args) throws FileNotFoundException {
        SimpleParser sp = new SimpleParser();
        System.out.println(sp.getParsedSourceInJSON(SRC_DIRECTORY_PATH, SRC_FILE_PATH));
    }
}
