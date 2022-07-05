package pattern.prototype;

import java.util.ArrayList;

/**
 * implements Cloneable
 * 이 객체가 복제해도 된다. 라고 명시 해야 가능하다.
 * 하는 역할은 없지만, 이처럼 명시하는 역할을 마크 인터페이스 라고 한다.
 * */
public class BookShelf implements Cloneable {
    private ArrayList<Book> shelf;

    public BookShelf() {
        shelf = new ArrayList<>();
    }

    public void addBook(Book book){
        shelf.add(book);
    }

    public ArrayList<Book> getShelf() {
        return shelf;
    }

    public void setShelf(ArrayList<Book> shelf) {
        this.shelf = shelf;
    }

/*  얕은 복사
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }*/

    @Override // 깊은 복사
    protected Object clone() throws CloneNotSupportedException {
        BookShelf another = new BookShelf();
        for(Book book : shelf){
            another.addBook(new Book(book.getAuthor(), book.getTitle()));
        }
        return another;
    }

    @Override
    public String toString() {
        return shelf.toString();
    }
}
