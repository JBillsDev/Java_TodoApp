package todo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class EntryData {
    @JsonPropertyOrder({"date", "text", "complete"})

    public String date;
    public String text;
    public boolean complete;

    // Required for Json deserialization.
    @SuppressWarnings("unused")
    public EntryData() {}

    public EntryData(String date, String text, boolean complete) {
        this.date = date;
        this.text = text;
        this.complete = complete;
    }
}
