package org.example.queries.search;

public class Page {

    private int size;
    private int pageNumber;

    public Page(int size, int pageNumber) {
        this.size = size;
        this.pageNumber = pageNumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
