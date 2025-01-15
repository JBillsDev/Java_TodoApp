package todo;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ListEntry extends JPanel {
    final JLabel labelDate;
    final JLabel labelText;
    final JCheckBox isComplete;

    public ListEntry(String date, final String entry, final boolean complete, final IEntryNotifier entryNotifier) {
        final var height = 32;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);

        setBackground(Color.GRAY);
        setBorder(BorderFactory.createLineBorder(Color.WHITE));

        Globals.setComponentSize(this, Globals.windowWidth, height);

        labelDate = new JLabel(date);
        if (date.isEmpty()) {
            final var labelDateText = getDateStamp();
            labelDate.setText(labelDateText);
        }

        labelDate.setOpaque(true);
        labelDate.setBackground(Color.LIGHT_GRAY);
        Globals.setComponentSize(labelDate, 100, height);
        labelDate.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        add(labelDate);

        labelText = new JLabel(entry);
        labelText.setOpaque(true);
        labelText.setBackground(Color.LIGHT_GRAY);
        Globals.setComponentSize(labelText, 308, height);
        labelText.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        add(labelText);

        isComplete = new JCheckBox();
        Globals.setComponentSize(isComplete, 32, height);
        isComplete.setBackground(Color.LIGHT_GRAY);
        isComplete.addActionListener(_ -> {
            labelText.setForeground(isComplete.isSelected() ? Color.GRAY : Color.BLACK);
            entryNotifier.notifyCompletionChanged();
        });

        if (complete) {
            isComplete.setSelected(true);
            labelText.setForeground(Color.GRAY);
        }

        add(isComplete);

        final var buttonDelete = new JButton("X");
        Globals.setComponentSize(buttonDelete, 42, height);
        buttonDelete.addActionListener(_ -> entryNotifier.removeEntry(ListEntry.this));
        add(buttonDelete);
    }

    public boolean isCompleted() {
        return isComplete.isSelected();
    }

    public String getDate() { return labelDate.getText(); }
    public String getText() {
        return labelText.getText();
    }

    private String getDateStamp() {
        final var date = LocalDate.now();
        final var day = date.getDayOfMonth();
        final var month = date.getMonthValue();

        var dateStamp = "";
        if (day < 10) dateStamp += "0";
        dateStamp += day + "-";

        if (month < 10) dateStamp += "0";
        dateStamp += month + "-";

        dateStamp += date.getYear();
        return dateStamp;
    }
}
