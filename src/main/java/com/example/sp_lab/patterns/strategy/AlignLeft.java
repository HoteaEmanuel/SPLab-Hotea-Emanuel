package com.example.sp_lab.patterns.strategy;

public class AlignLeft implements AlignStrategy {
    private final int context;

    public AlignLeft(int context) {
        if (context <= 0) {
            throw new IllegalArgumentException("context must be positive");
        }
        this.context = context;
    }

    @Override
    public void render(Paragraph p) {
        String text = p.getText();
        for (int i = 0; i < text.length(); i += context) {
            System.out.println(text.substring(i, Math.min(i + context, text.length())));
        }
    }
}
