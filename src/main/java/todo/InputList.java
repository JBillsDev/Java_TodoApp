package todo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InputList extends JScrollPane {
    private final JPanel mainPanel;
    private final ArrayList<ListEntry> entries = new ArrayList<>();

    private final IEntryNotifier entryNotifier;

    public InputList(final IEntryNotifier entryNotifier) {
        this.entryNotifier = entryNotifier;

        setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        getViewport().setBackground(Color.DARK_GRAY);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.DARK_GRAY);
        setViewportView(mainPanel);
    }

    public void addEntry(final ListEntry entry) {
        mainPanel.add(entry);
        entries.add(entry);

        final var completeCount = getCompleteCount();
        entryNotifier.notifyEntryCountChanged(mainPanel.getComponentCount(), completeCount);
    }

    public boolean hasDuplicateEntry(final String text) {
        for (final var entry : entries) {
            if (entry.getText().equals(text)) return true;
        }

        return false;
    }

    public void addLoadedEntries(ArrayList<EntryData> entries) {
        for (final var entry : entries) {
            var listEntry = new ListEntry(entry.date, entry.text, entry.complete, entryNotifier);

            mainPanel.add(listEntry);
            this.entries.add(listEntry);
        }

        final var completeCount = getCompleteCount();
        entryNotifier.notifyEntryCountChanged(mainPanel.getComponentCount(), completeCount);
    }

    public void removeEntry(final ListEntry entry) {
        mainPanel.remove(entry);
        if (entries.contains(entry)) {
            final var index = entries.indexOf(entry);
            entries.set(index, null);
            entries.remove(index);
        }

        var completeCount = getCompleteCount();
        entryNotifier.notifyEntryCountChanged(mainPanel.getComponentCount(), completeCount);
    }

    public int getEntryCount() {
        return mainPanel.getComponentCount();
    }

    public int getCompleteCount() {
        var count = 0;
        for (final var entry : entries) {
            if (entry.isCompleted()) count++;
        }

        return count;
    }

    public ArrayList<EntryData> getEntries() {
        var dataEntries = new ArrayList<EntryData>();
        for (final var entry : entries) {
            dataEntries.add(new EntryData(entry.getDate(), entry.getText(), entry.isCompleted()));
        }

        return dataEntries;
    }
}
