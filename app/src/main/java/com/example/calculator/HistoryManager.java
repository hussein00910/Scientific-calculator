package com.example.calculator;

import java.util.ArrayList;
import java.util.List;

public class HistoryManager {

    public static class Entry {
        public final String expression;
        public final String result;
        Entry(String expression, String result) {
            this.expression = expression;
            this.result = result;
        }
    }

    private static final int MAX_ENTRIES = 100;
    private final List<Entry> entries = new ArrayList<>();

    public void add(String expression, String result) {
        if (expression == null || expression.isEmpty()) return;
        entries.add(0, new Entry(expression, result));
        if (entries.size() > MAX_ENTRIES) entries.remove(entries.size() - 1);
    }

    public List<Entry> getAll() { return entries; }

    public void clear() { entries.clear(); }
}
