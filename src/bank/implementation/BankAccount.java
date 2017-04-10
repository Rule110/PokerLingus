package bank.implementation;

class BankAccount {
    private int funds = 0;
    
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
