package pattern.prototype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ProtoTypeTest {

    @Test
    void 얕은복사() throws CloneNotSupportedException {
        BookShelf bookShelf = getBookShelf();

        Assertions.assertEquals(bookShelf.getShelf().size(), 3);

        BookShelf another = (BookShelf) bookShelf.clone();
        Assertions.assertEquals(another.getShelf().size(), 3);

        Assertions.assertNotEquals(bookShelf, another); // 객체가 가리키는 주소값은 다름
        Assertions.assertEquals(bookShelf.toString(), another.toString());
   }


    @Test
    void 깊은복사() throws CloneNotSupportedException {
        BookShelf bookShelf = getBookShelf();
        BookShelf another = (BookShelf) bookShelf.clone();

        // 원본의 변경
        bookShelf.getShelf().get(0).setAuthor("조정래");
        bookShelf.getShelf().get(0).setTitle("태백산맥");

        Assertions.assertNotEquals(bookShelf, another);
        Assertions.assertNotEquals(bookShelf.toString(), another.toString()); // 원본 변화로인해 복사본 이 달라지지않음.(깊은 복사)
    }

    private BookShelf getBookShelf() {
        BookShelf bookShelf = new BookShelf();
        bookShelf.addBook(new Book("박완서", "친절한 복희씨"));
        bookShelf.addBook(new Book("박완서", "친절한 복희씨2"));
        bookShelf.addBook(new Book("박완서", "친절한 복희씨3"));
        return bookShelf;
    }
}
