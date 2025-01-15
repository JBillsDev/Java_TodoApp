package todo;

import javax.swing.*;
import java.awt.*;

public class Todo extends JPanel implements IEntryNotifier {
    private final CompletionPanel completionPanel;
    private final InputForm inputForm;
    private final InputList inputList;

    public Todo() {
        {
            Globals.setComponentSize(this, Globals.windowWidth, 500);

            setBackground(Color.DARK_GRAY);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }

        completionPanel = new CompletionPanel();
        inputForm = new InputForm(this);
        inputList = new InputList(this);

        inputList.addLoadedEntries(FileIO.readFile());

        add(inputForm);
        add(inputList);
        add(completionPanel);
    }

    @Override
    public void addEntry(final String entry) {
        inputList.addEntry(new ListEntry("", entry, false, this));
        save();

        revalidate();
        repaint();
    }

    @Override
    public void checkForDuplicateEntry(final String entry) {
        inputForm.setEntryIsDuplicate(inputList.hasDuplicateEntry(entry));
    }

    @Override
    public void notifyCompletionChanged() {
        final var entryCount = inputList.getEntryCount();
        final var completeCount = inputList.getCompleteCount();
        save();

        completionPanel.setEntryCount(entryCount, completeCount);
    }

    @Override
    public void notifyEntryCountChanged(final int entryCount, final int completeCount) {
        completionPanel.setEntryCount(entryCount, completeCount);
    }

    @Override
    public void removeEntry(final ListEntry entry) {
        inputList.removeEntry(entry);
        save();

        revalidate();
        repaint();
    }

    public void save() {
        FileIO.writeFile(inputList.getEntries());
    }
}
