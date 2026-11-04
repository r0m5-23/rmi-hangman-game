package hangmangameserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceInterface extends Remote{
    
    public void addPlayer(String username) throws RemoteException;
    public String getWord() throws RemoteException;
    public String guessWord(String guessword) throws RemoteException;
    public int getLifes() throws RemoteException;
    public String getUsedLetters() throws RemoteException;
    public boolean getStatus() throws RemoteException;
}
