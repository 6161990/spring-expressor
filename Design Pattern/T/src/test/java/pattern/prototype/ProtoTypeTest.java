package pattern.prototype;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProtoTypeTest {

    @Test
    void name() throws CloneNotSupportedException {
        BookShelf bookShelf = new BookShelf();
        bookShelf.addBook(new Book("박완서", "친절한 복희씨"));
        bookShelf.addBook(new Book("박완서", "친절한 복희씨2"));
        bookShelf.addBook(new Book("박완서", "친절한 복희씨3"));

        Assertions.assertEquals(bookShelf.getShelf().size(), 3);

        BookShelf another = (BookShelf) bookShelf.clone();
        Assertions.assertEquals(another.getShelf().size(), 3);


    }
}
