package declaration.attribute_declaration;

import java.util.ArrayList;
import java.util.List;

public class Cow {

    int age;

    public int length = 40;

    final String name = "Kitty";

    private CowEye leftEye;

    protected static final CowEye rightEye = new CowEye("blue");

    List<CowEye> cowEyes = new ArrayList<>();

}
