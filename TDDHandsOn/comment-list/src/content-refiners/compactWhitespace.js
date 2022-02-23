function compactWhitespace(value) {
    return value.indexOf("  ") < 0 // 중복되는 공백 문자가 있다면
        ? value
        : compactWhitespace(value.replace("  ", " ")); // 한번의 공백문자로 줄인다
}

export default compactWhitespace;