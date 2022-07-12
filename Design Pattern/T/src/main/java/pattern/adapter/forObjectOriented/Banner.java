package pattern.adapter.forObjectOriented;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Banner {
    private String str;

    public String showWithParen(){
        return "("+str+")";
    }

    public String showWithAster(){
        return "**"+str+"**";
    }
}
