package hangmangameserver;

import java.util.LinkedList;

public class Handler {
    
    LinkedList<Player> playerList = new LinkedList<>();
    
    public void addObject(Player p){
        this.playerList.add(p);
    }
    
    public void removeObject(Player p){
        this.playerList.remove(p);
    }
}
