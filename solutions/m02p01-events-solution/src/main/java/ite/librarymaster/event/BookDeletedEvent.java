package ite.librarymaster.event;

public class BookDeletedEvent {
    public Long id;
    public String catId;
    public String title;
    public BookGenre genre;

    @Override
    public String toString() {
        return "BookDeletedEvent{" +
                "id=" + id +
                ", catId='" + catId + '\'' +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                '}';
    }
}
