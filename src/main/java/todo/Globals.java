package todo;

import javax.swing.*;
import java.awt.*;

public record Globals() {
    public final static int windowWidth = 500;

    public static void setComponentSize(JComponent component, int width, int height) {
        var size = new Dimension(width, height);

        component.setMaximumSize(size);
        component.setMinimumSize(size);
        component.setPreferredSize(size);
    }
}
