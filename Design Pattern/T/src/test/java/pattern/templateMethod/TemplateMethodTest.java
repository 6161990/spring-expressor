package pattern.templateMethod;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TemplateMethodTest {

    Player player;

    @BeforeEach
    void setUp(){
        player = new Player(0);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 7})
    void 정해진_숫자_만큼_jump를_시도합니다(int count) {
        player = new Player(count);
        player.play(0);
        String message = player.showMessage();

        Assertions.assertEquals(message,  "***** Beginner Level *****" + count + "round jumping");
    }

    @Test
    void superLevel은_훅_메소드를_구현하여_fly_합니다() {
        player.upgradeLevel(new SuperLevel());
        player.play(1);
        String message = player.showMessage();
        Assertions.assertEquals(message,  "***** SuperLevel Level *****" + 1 + "round jumping and flying!!!!!!");
    }

    class Player {
        private PlayerLevel playerLevel;
        private int count;

        public Player(int count){
            playerLevel = new BeginnerLevel();
            this.count = count;
        }

        public void upgradeLevel(PlayerLevel playerLevel){
            this.playerLevel = playerLevel;
        }

        public PlayerLevel getPlayerLevel(){
            return playerLevel;
        }

        public void play(int addingCount){
            count += addingCount;
            playerLevel.go(count);
        }

        public String showMessage(){
            return playerLevel.showLevelMessage(count);
        }
    }
}
