package bank.implementation;

/**
 * This class models a conceptual bank account in which
 *  users chips are stored for the duration of a game.
 */
class BankAccount{
	
	private int funds = 0;
	
	/**
	 * Constructor for bank account which sets initial funds
	 * @param funds
	 */
	public BankAccount(int funds){
		this.funds = funds;
	}
    
	/**
	 * Getter returns the amount of chips the player has available
	 * @return
	 */
    int getAvailableFunds(){
        return this.funds;
    }
    
    /**
     * Method to remove an amount of chips from the players account specified by the parameter.
     * @param withdrawal
     */
    void withdraw(int withdrawal){
        this.funds -= withdrawal;
    }
    
    /**
     * Method to add an amount of chips from the players account specified by the parameter.
     * @param deposit
     */
    void deposit(int deposit){
        this.funds += deposit;
    }
}
