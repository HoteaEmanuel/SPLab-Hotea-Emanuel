package com.example.sp_lab;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// Adds a "type" property to each element's JSON so clients can tell the kinds apart (e.g. an empty Table from a TableOfContents)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Paragraph.class, name = "paragraph"),
        @JsonSubTypes.Type(value = Section.class, name = "section"),
        @JsonSubTypes.Type(value = Image.class, name = "image"),
        @JsonSubTypes.Type(value = ImageProxy.class, name = "imageProxy"),
        @JsonSubTypes.Type(value = Table.class, name = "table"),
        @JsonSubTypes.Type(value = TableOfContents.class, name = "tableOfContents")
})
public interface Element {
    void print();

    void addElement(Element e);

    Element getElement(int i);

    void removeElement(Element e);

    void accept(Visitor v);
}
