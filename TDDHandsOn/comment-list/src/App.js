import { useState } from "react";
import CommentList from "./CommentList";
import {Form} from "./Form";

function App({ commentComposer }) {

  const [comments, setComments] = useState([]);

  function onNewComment(newComment) {
    setComments([...[...comments], newComment]);
  }

  return (
    <div>
      <Form commentComposer={commentComposer} onNewComment={onNewComment} />
      <CommentList comments={comments}/>
    </div>
  );
}

export default App;
