import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import commentComposerFactory from "./commentComposerFactory";
import commentRefiner from "./content-refiners/compactWhitespace";

ReactDOM.render(
  <React.StrictMode>
    <App commentComposer={commentComposerFactory(commentRefiner)} />
  </React.StrictMode>,
  document.getElementById("root")
);
