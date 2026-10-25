package com.example.sp_lab;

public class GenerateToC implements Visitor {
    // Ma gandeam ca o pagina sa poata avea maxim 3 paragrafe pentru simplificare
    private static final int MAX_PARAGRAPHS_PER_PAGE = 3;

    // In rest celelalte: imagine, tabel, sa ia o intreaga pagina


    private final TableOfContents toc = new TableOfContents();
    private int page = 1;
    private int paragraphs;

    public TableOfContents getToC() {
        return toc;
    }

    @Override
    public void visit(Book book) {
    }

    @Override
    public void visit(Section section) {
        // a section is listed on the page that is current when it is reached
        toc.addEntry(section, page);
    }

    @Override
    public void visit(TableOfContents tableOfContents) {
    }

    @Override
    public void visit(Paragraph paragraph) {
        if (paragraphs == MAX_PARAGRAPHS_PER_PAGE) nextPage();
        paragraphs++;
    }

    @Override
    public void visit(ImageProxy imageProxy) {
        placeImage();
    }

    @Override
    public void visit(Image image) {
        placeImage();
    }

    @Override
    public void visit(Table table) {
        nextPage();
    }

    private void placeImage() {
        nextPage();
    }

    private void nextPage() {
        page++;
        paragraphs = 0;
    }
}