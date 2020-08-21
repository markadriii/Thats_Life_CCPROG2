public class Player {
	private int playerNumber; // From 0 - 3
	private double moneyBalance;
	private double moneyLoan;
	private double moneyLoanInterest;
	
	private double salary;
	private int chosenPath;
	private String career;
	
	private boolean graduated;
	private int numberOfOffsprings;
	
	private int currentSpace; // For the player's current position on the board. Not yet used since I still have to learn javafx and MVC architecture.	
	
	public Player(int playerNumber) {
		this.playerNumber = playerNumber;
		this.moneyBalance = 200000.00;
	}
	
	public void addMoneyBalance(double amount) {
		this.moneyBalance += amount;
	}
	
	public void addLoan() {
		moneyLoan += 20000.00;
		moneyBalance += 20000.00;
		moneyLoanInterest += 5000;
	}
	
	public void reduceMoneyBalance(double amount) {
		if (moneyBalance - amount < 0) {
			addLoan();
			moneyBalance -= amount;
		}
		else
			moneyBalance -= amount;
	}
	
	public boolean payLoan() {
		if (moneyBalance - (moneyLoan + moneyLoanInterest) < 0) {
			return false;
		} 
		else {
			moneyBalance -= (moneyLoan + moneyLoanInterest);
			moneyLoan = 0;
			moneyLoanInterest = 0;
			return true;
		}
	}
	
	public void setCareer(String career) {
		this.career = career; 
	}
	
	public String getCareer() {
		return this.career;
	}
	
	public double getMoneyBalance() {
		return this.moneyBalance;
	}
	
	public double getMoneyLoan() {
		return this.moneyLoan;
	}
}
