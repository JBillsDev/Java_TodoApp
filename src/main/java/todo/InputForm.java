package todo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputForm extends JPanel {
    private final JButton buttonAdd;
    private final JTextField textField;
    private final IEntryNotifier entryNotifier;

    public InputForm(final IEntryNotifier entryNotifier) {
        this.entryNotifier = entryNotifier;

        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.GRAY);

        Globals.setComponentSize(this, Globals.windowWidth, 34);

        textField = new JTextField(20);
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {}

            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (buttonAdd.isEnabled())
                    {
                        submitTextField();
                    }

                    return;
                }

                var text = textField.getText();

                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && !text.isEmpty()) {
                    text = text.substring(0, text.length() - 1);
                } else if (e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
                    text += e.getKeyChar();
                }

                entryNotifier.checkForDuplicateEntry(text);
            }

            @Override
            public void keyReleased(final KeyEvent e) {}
        });
        add(textField);

        buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(_ -> submitTextField());
        add(buttonAdd);

        final var buttonClear = new JButton("Clear");
        buttonClear.addActionListener(_ -> {
            clearTextField();
            textField.requestFocusInWindow();
        });
        add(buttonClear);
    }

    private void clearTextField() {
        textField.setText("");
    }

    public void setEntryIsDuplicate(final boolean isDuplicate) {
        buttonAdd.setEnabled(!isDuplicate);
    }

    private void submitTextField() {
        if (textField.getText().isEmpty()) return;

        entryNotifier.addEntry(textField.getText());
        clearTextField();
    }
}
