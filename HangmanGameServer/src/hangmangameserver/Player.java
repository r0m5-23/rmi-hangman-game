package hangmangameserver;

public class Player {
    
    private String username;
    private ID id;
    
    public Player(String username, ID id){
        this.username = username;
        this.id = id;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setId(ID id){
        this.id = id;
    }
    
    public ID getId(){
        return id;
    }
}
