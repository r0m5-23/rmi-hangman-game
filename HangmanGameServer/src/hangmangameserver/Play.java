package hangmangameserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Random;

public class Play {
    
    private final int MAXLIFE = 6;
    
    private final Random random = new Random();
    private int nWords;
    private final ArrayList<String> words = new ArrayList<>();
    private int draw;
    private String gameWord;
    private String dashedWord = "";
    private char[] checkWord;
    private String usedLetters = "";
    private char letter;
    private boolean win = false;
    private int lifes = 6;
    private String guess;
    private boolean loseLife;

    // GET LIFES
    public int getLifes() {
        return lifes;
    }

    // GET BOOLEAN WIN
    public boolean isWin() {
        return win;
    }

    // GET USEDLETTERS
    public String getULetters() {
        return usedLetters;
    }

    // RESET VARIABLES
    public void reset() {
        this.lifes = MAXLIFE;
        this.dashedWord = "";
        this.usedLetters = "";
        this.win = false;
    }

    // GET THE WORDS OFF HANGMAN.TXT
    public ArrayList<String> addList() {

        // InputStream inputStream =
        // Play.class.getClassLoader().getResourceAsStream("/WordList.txt");
        // InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        // OPEN WORDLIST.TXT AND SAVE THE NUMBER OFF LINES
        try (LineNumberReader lnr = new LineNumberReader(new FileReader("WordList.txt"))) {
            lnr.skip(Long.MAX_VALUE);
            nWords = lnr.getLineNumber() + 1;
            lnr.close();
        } catch (final IOException ex) {
            ex.printStackTrace();
        }

        // OPEN WORDLIST.TXT AND SAVE THE WORDS IN ARRAY
        try (BufferedReader bfr = new BufferedReader(new FileReader("WordList.txt"))) {
            String Linha;
            while ((Linha = bfr.readLine()) != null) {
                words.add(Linha);
            }
            bfr.close();
        } catch (final IOException ex) {
            ex.printStackTrace();
        }

        return words;
    }

    // GET THE GAMEWORD
    public String GameWord() {
        // RANDOM ONE LINE AND GET THE RESPECTIVE WORD
        draw = random.nextInt(nWords);
        gameWord = words.get(draw);
        checkWord = new char[gameWord.length()];
        return gameWord;
    }

    // GET THE DASHEDWORD "_ _ _ _ _ _"
    public String DashedWord() {

        // CHAR ARRAY CHECKWORD GET THE WORD LENGHT AND FOR EACH POSITION LETTER IS
        // INNITIALIZED WITH A (0) IN THE ARRAY
        for (int i = 0; i < checkWord.length; i++) {
            checkWord[i] = 0;
        }
        // FOR EACH POSITION OF THE GAMEWORD LENGTH
        // GAMEWORD IS 'CONVERTED' TO A NEW STRING DASHEDWORD, THAT FOR POSITION ADD A
        // DASH "_"
        for (int i = 0; i < gameWord.length(); i++) {
            dashedWord += " _ ";
        }
        return dashedWord;
    }

    // GET THE NEWDASHEDWORD "R _ _ T _ R" EACH TIME THAT RECEIVE A GUESS
    public String GuessWord(String guessw) {
        
        this.guess = guessw;
        String newDashedWord = "";
        loseLife = true;
            // IF THE GUESS HAVE MORE THAN ONE LETTER, THE USER IS TRYING TO GO FOR THE WORD
            if (guess.length() > 1){
                // IF GUESS EQUALS THE GAMEWORD
                if(guess.equals(gameWord)){
                    // FOR EACH POSITION OF THE GAMEWORD LENGTH, LETTER EQUALS TO THE CHAR LETTER OF THE GUESS
                    for (int i=0; i < gameWord.length(); i++){    
                        letter = guess.charAt(i);
                        // IF LETTER EQUALS TO EACH LETTER OF GAMEWORD 
                        // CHAR ARRAY CHECKWORD FOR EACH POSITION GETS (1)
                        // DON'T LOSE LIFE
                        // WIN THE GAME
                        if (letter == gameWord.charAt(i)){
                            checkWord[i] = 1;
                            loseLife = false;
                            win = true;
                        }        
                    }   
                // ELSE LOSES ALL LIFES    
                } else if (loseLife){
                    lifes = 0;
                }
            // ELSE IF THE GUESS IS (0), DON'T LOSE LIFE, BECAUSE USER IS TRYING TO QUIT THE GAME  
            } else if (guess.equals("0")) {
                loseLife = false;
            // ELSE THE GUESS ONLY HAVE ON LETTER, WICH IS SAVE 
            } else {
                letter = guess.charAt(0);
                // EACH LETTER THAT THE USER GUESS IS SAVE TO SHOW THE USED LETTERS
                usedLetters += " " + letter;
                // FOR EACH POSITION OF THE GAMEWORD LENGTH
                for (int i=0; i < gameWord.length(); i++){    
                    // IF LETTER EQUALS TO EACH GAMEWORD CHAR LETTER
                    // CHAR ARRAY CHECKWORD FOR EACH POSITION GETS (1)
                    // DON'T LOSE LIFE
                    if (letter == gameWord.charAt(i)){
                        checkWord[i] = 1;
                        loseLife = false;
                    }        
                }    
                // LOSE A LIFE
                if(loseLife){
                    lifes--;   
                }
            }
            
            win = true;
            
            // FOR EACH POSITION OF THE GAMEWORD LENGTH
            for (int i=0; i < gameWord.length(); i++){
                // IF CHAR ARRAY CHECKWORD FOR EACH POISTION IS EQUAL TO (0)
                // NEWDASHEDWORD IS UPDATED WITH A DASH
                // DON'T WIN THE GAME
                if (checkWord[i] == 0){
                    newDashedWord += " _ ";
                    win = false; 
                // ELSE NEWDASHEDWORD IS UPDATED WITH THE LETTER OF THE GAMEWORD THAT WAS RIGHT
                } else {
                    newDashedWord += (" " + gameWord.charAt(i) + " ");
                }    
            }    

            return newDashedWord;
        }
}
