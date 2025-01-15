package todo;

public interface IEntryNotifier {
    void addEntry(final String entry);
    void checkForDuplicateEntry(final String entry);
    void notifyCompletionChanged();
    void notifyEntryCountChanged(final int entryCount, final int completeCount);
    void removeEntry(final ListEntry entry);
}
