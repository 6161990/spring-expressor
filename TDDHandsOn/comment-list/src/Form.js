import { useState } from "react";

export function Form({ commentComposer }) {
    const [author, setAuthor] = useState("");
    const [content, setContent] = useState("");


    const handleSubmit = (e) => {
        e.preventDefault();
        const newComment = commentComposer({ author, content }); // 사용 후 결과물을 newComment에 입력하고 있다.(간접입력)
        onNewComment(newComment); // 데이터 입력 뿐, 반환 결과를 출력하고 있지 않다. form 입장에서는 출력, onNewComment입장에서는 입력 -> onNewComment는 Form 에게 간접출력 인터페이스를 제공하고 있다.
        setAuthor("");
        setContent("");
    };

    return <form onSubmit={handleSubmit}>
        <input
            name="author"
            value={author}
            type="text"
            onChange={(e) => setAuthor(e.currentTarget.value)}
            placeholder="작성자"
        />
        <input
            name="content"
            value={content}
            type="text"
            onChange={(e) => setContent(e.currentTarget.value)}
            placeholder="내용"
        />
        <button name="submit">입력</button>
    </form>;
}

// 분리함으로써
// 앱 내부에서 정의하고 있는 코드를 직접사용하지않고 입력받아 사용하게한 것.
// 입력 과정에서 Form 요소는 commentComposer, onNewComment의존성을 갖게되었고
// commentComposer는 간접 입력을 제공하는 의존성을, onNewComment는 간접 출력을 제공하는 의존성을 갖게 되었다.