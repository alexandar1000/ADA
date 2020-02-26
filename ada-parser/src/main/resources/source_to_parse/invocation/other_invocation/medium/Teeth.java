package invocation.other_invocation.medium;

import com.google.common.base.Strings;

public class Teeth {

    int width = 1;

    static String type = Strings.repeat("t", 3);

    public Teeth() {

    }

    public String getType() {
        return type;
    }

    static String staticGetType() {
        return type;
    }
}
