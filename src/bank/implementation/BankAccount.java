package bank.implementation;


class BankAccount{
	
	private int funds = 0;
	
	public BankAccount(int funds){
		this.funds = funds;
	}
    
    int getAvailableFunds(){
        return this.funds;
    }
    
    void withdraw(int withdrawal){
        this.funds -= withdrawal;
    }
    
    void deposit(int deposit){
        this.funds += deposit;
    }
}
