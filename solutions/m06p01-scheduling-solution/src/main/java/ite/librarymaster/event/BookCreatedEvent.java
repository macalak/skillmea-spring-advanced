package ite.librarymaster.event;

public class BookCreatedEvent {
    public Long id;
    public String catId;
    public String title;
    public String publisher;
    public String author;
    public String isbn;
    public BookGenre genre;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BookCreatedEvent{");
        sb.append("id=").append(id);
        sb.append(", catId='").append(catId).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", publisher='").append(publisher).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append(", genre=").append(genre);
        sb.append('}');
        return sb.toString();
    }
}
