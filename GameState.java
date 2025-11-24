public class GameState {
    public int player, difficulty, stage, life, bomb, itemValue, power, graze;
    public GameState(int player, int difficulty, int stage, int life, int bomb, int itemValue, int power, int graze) {
        this.player = player;
        this.difficulty = difficulty;
        this.stage = stage;
        this.life = life;
        this.bomb = bomb;
        this.itemValue = itemValue;
        this.power = power;
        this.graze = graze;
    }
}
