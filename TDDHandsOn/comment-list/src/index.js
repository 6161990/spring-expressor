import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import compositeContentRefinerFactory from "./content-refiners/compositeContentRefinerFactory";
import compactWhitespace from "./content-refiners/compactWhitespace";
import trimWhitespaces from "./content-refiners/trimWhitespaces";
import commentComposerFactory from "./commentComposerFactory";

const commentRefiner = compositeContentRefinerFactory([
    compactWhitespace,
    trimWhitespaces,
]);
ReactDOM.render(
  <React.StrictMode>
    <App commentComposer={commentComposerFactory(commentRefiner)} />
  </React.StrictMode>,
  document.getElementById("root")
);
