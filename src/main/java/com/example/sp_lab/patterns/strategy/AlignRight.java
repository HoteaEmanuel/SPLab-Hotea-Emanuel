package com.example.sp_lab.patterns.strategy;

public class AlignRight implements AlignStrategy {
    private final int context;

    public AlignRight(int context) {
        if (context <= 0) {
            throw new IllegalArgumentException("context must be positive");
        }
        this.context = context;
    }

    @Override
    public void render(Paragraph p) {
        String text = p.getText();
        for (int i = 0; i < text.length(); i += context) {
            String line = text.substring(i, Math.min(i + context, text.length()));
            int empty = context - line.length();
            System.out.println(" ".repeat(empty) + line);
        }
    }
}
