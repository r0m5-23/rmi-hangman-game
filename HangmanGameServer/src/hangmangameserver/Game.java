package hangmangameserver;

public class Game implements ServiceInterface {

    private String username;
    private String guessWord;
    private String newDashedWord;
    private Handler handler = new Handler();
    private Play play = new Play();

    @Override
    public void addPlayer(String username) {
        this.username = username;

        handler.playerList.add(new Player(this.username, ID.Player));
        System.out.println("\n" + this.username + " has join the party!");
    }

    @Override
    public String getWord() {
        play.reset();
        play.addList();
        play.GameWord();
        return play.DashedWord();
    }

    @Override
    public String guessWord(String guessword) {
        this.guessWord = guessword;
        newDashedWord = play.GuessWord(this.guessWord);
        return newDashedWord;
    }

    @Override
    public int getLifes() {
        return play.getLifes();
    }

    @Override
    public String getUsedLetters() {
        return play.getULetters();
    }

    @Override
    public boolean getStatus() {
        return play.isWin();
    }
}
