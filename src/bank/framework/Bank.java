package bank.framework;
//change
public interface Bank {
    public int getAvailableFunds(String playerID);
    
    public void withdraw(String playerID, int withdrawal);
    
    public void deposit(String playerID, int deposit);
}
//