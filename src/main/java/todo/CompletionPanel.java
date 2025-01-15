package todo;

import javax.swing.*;
import java.awt.*;

public class CompletionPanel extends JPanel {
    final JLabel labelEntryCount;
    final JLabel labelCompletedCount;
    final JLabel labelIncompleteCount;
    final JLabel labelPercentage;

    public CompletionPanel() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        Globals.setComponentSize(this, Globals.windowWidth, 34);

        labelEntryCount = addLabel("Entries: 0");
        labelCompletedCount = addLabel("Complete: 0");
        labelIncompleteCount = addLabel("Incomplete: 0");
        labelPercentage = addLabel("Percentage: 0%");
    }

    public void setEntryCount(final int entryCount, final int completeCount) {
        labelEntryCount.setText("Entries: " + entryCount);
        labelCompletedCount.setText("Complete: " + completeCount);
        labelIncompleteCount.setText("Incomplete: " + (entryCount - completeCount));

        if (entryCount == 0) {
            labelPercentage.setText("Percentage: 0%");
        } else {
            labelPercentage.setText("Percentage: " + (completeCount * 100 / entryCount) + "%");
        }
    }

    private JLabel addLabel(final String text) {
        final var labelBorder = BorderFactory.createEmptyBorder(0, 10, 0, 10);

        final var label = new JLabel(text);
        Globals.setComponentSize(label, Globals.windowWidth / 4, 34);
        label.setBorder(labelBorder);
        add(label);

        return label;
    }
}
