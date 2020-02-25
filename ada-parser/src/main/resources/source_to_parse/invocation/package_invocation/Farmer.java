package invocation.package_invocation;

import com.google.common.base.Strings;
import declaration.package_declaration.Snake;
import invocation.attribute_invocation.*;

import java.util.Set;

public class Farmer {

    Horse horse;

    HorseColor horseColor;

    Donkey donkey;

    Snake snake;

    Set<String> set;

    void farm() {
        Strings.repeat("HEY", 5);
    }
}
