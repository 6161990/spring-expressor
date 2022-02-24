import { v4 as uuidv4 } from "uuid";

function commentComposerFactory(commentRefiner) { // 외부로부터 commentRefiner 를 직접입력 받는다. - 함수의 파라미터 즉, 공개된 인터페이스로 입력을 받기 때
  return ({ author, content }) => {
    return {
      id: uuidv4(),
      author,
      content: commentRefiner(content), // commentRefiner 입장에서는 출력이지만, commentComposerFactory 입장에서는 (간접)입력
      createdTime: new Date(),
    };
  };
}

export default commentComposerFactory;
