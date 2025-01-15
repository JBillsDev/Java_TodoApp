import todo.FileIO;
import todo.Todo;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {
        final var window = createAndReturnWindow();
        window.add(new Todo());

        finalizeWindow(window);
    }

    private static JFrame createAndReturnWindow() {
        final var window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("To-Do");
        window.setResizable(false);

        return window;
    }

    private static void finalizeWindow(final JFrame window) {
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
