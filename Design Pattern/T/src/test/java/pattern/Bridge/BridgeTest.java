package pattern.Bridge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pattern.bridge.impl.ArrayImpl;
import pattern.bridge.impl.LinkedListImpl;
import pattern.bridge.list.Queue;
import pattern.bridge.list.Stack;

import static org.assertj.core.api.Assertions.assertThat;

public class BridgeTest {

    @Test
    void Bridge패턴을_이용해_LinkedList로_Stack을_구현합니다() {
        Stack<String> linkedListStack = new Stack<>(new LinkedListImpl<>());
        linkedListStack.push("AAA");
        linkedListStack.push("BBB");
        linkedListStack.push("CCC");

        String first = linkedListStack.pop();
        String second = linkedListStack.pop();
        String third = linkedListStack.pop();

        assertThat(first).isEqualTo("CCC");
        assertThat(second).isEqualTo("BBB");
        assertThat(third).isEqualTo("AAA");
    }

    @Test
    void Bridge패턴을_이용해_Array로_Stack을_구현합니다() {
        Stack<String> arrayStack = new Stack<>(new ArrayImpl<>());
        arrayStack.push("AAA");
        arrayStack.push("BBB");
        arrayStack.push("CCC");

        String first = arrayStack.pop();
        String second = arrayStack.pop();
        String third = arrayStack.pop();

        assertThat(first).isEqualTo("CCC");
        assertThat(second).isEqualTo("BBB");
        assertThat(third).isEqualTo("AAA");
    }

    @Test
    void Bridge패턴을_이용해_Array로_Queue를_구현합니다() {
        Queue<String> arrayQueue = new Queue<>(new ArrayImpl<>());
        arrayQueue.enQueue("AAA");
        arrayQueue.enQueue("BBB");
        arrayQueue.enQueue("CCC");

        String first = arrayQueue.deQueue();
        String second = arrayQueue.deQueue();
        String third = arrayQueue.deQueue();

        assertThat(first).isEqualTo("AAA");
        assertThat(second).isEqualTo("BBB");
        assertThat(third).isEqualTo("CCC");
    }

    @Test
    void Bridge패턴을_이용해_LinkedList로_Queue를_구현합니다() {
        Queue<String> linkedQueue = new Queue<>(new LinkedListImpl<>());
        linkedQueue.enQueue("AAA");
        linkedQueue.enQueue("BBB");
        linkedQueue.enQueue("CCC");

        String first = linkedQueue.deQueue();
        String second = linkedQueue.deQueue();
        String third = linkedQueue.deQueue();

        assertThat(first).isEqualTo("AAA");
        assertThat(second).isEqualTo("BBB");
        assertThat(third).isEqualTo("CCC");
    }
}
