package com.example.sp_lab.patterns.strategy;

public class AlignCenter implements AlignStrategy {
    private final int context;

    public AlignCenter(int context) {
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
            int left = empty / 2;
            int right = empty - left;
            System.out.println(" ".repeat(left) + line + " ".repeat(right));
        }
    }
}
