import java.util.*;

/** JAVADOC
 * This class is for action cards.
 * It includes the mainID which is used to identify a card (see below).<P>
 * 0 - Collect from the Bank<P>
 * 1 - Pay the Bank<P>
 * 2 - Pay the Player<P>
 * 3 - Collect From Player<P>
 * There is also a subID that is used to further identify an action card (see below). <P>
 * If ID = 0 (Collect from bank) -> subID 0 - Tax refund, 1 - Sell an Item, 2 - Bonus Payday, 3 - Setup school <P>
 * If ID = 1 (Pay the Bank) -> subID 0 - Buy an Item, 1 - Visit a Place, 2 - Hiking, 3 - Watch a Show, 4 - Win a Competiton, 5 - Traffic Violation<P>
 * If ID = 2 (Pay the Player) -> subID 0 - Lawsuit (choose a Player), 1 - Christmas Bonus (pay all players) <P>
 * If ID = 3 (Collect from Player)-> subID 0 - File a Lawsuit (choose a player), 1 - It's Your Birthday (collect from all players) <P>
 * @author gabby
 */

public class ActionCard {
	private int mainID, subID;
	private String typeOfCard;
	private String description;
	private String toDoAction;
	
	private static int head; // stack implementation
	
	Scanner input = new Scanner(System.in);
	
	ActionCard(int randomNumber) {
		mainID = randomNumber;
		head = 49;
	}
	
	public void generateSubID() {
		Random generateRandom = new Random();
		
		switch (mainID) {
		case 0 : subID = generateRandom.nextInt(5); break; // Collect from Bank
		case 1 : subID = generateRandom.nextInt(6); break; // Pay the Bank
		case 2 : subID = generateRandom.nextInt(2); break; // Pay the Player
		case 3 : subID = generateRandom.nextInt(2); break; // Collect From Player
		}
	}
	
	public void assignDescriptions(int mainID) {
		if (mainID == 0) { // Collect from Bank
			switch (subID) {
				case 0 : {
					typeOfCard = "TAX REFUND";
					description = "Get your money back from the taxpayers!";
					break;
				}
				case 1 : {
					typeOfCard = "SELL AN ITEM";
					description = "Get rid of your item and get your money back!";
					break;
				}
				case 2 : { 
					typeOfCard = "BONUS PAYDAY";
					description = "Horray for bonus pay!";
					break;
				}
				case 3 : {
					typeOfCard = "SETUP SCHOOL";
					description = "Give good, get good money!";
					break;
				}
				case 4 : {
					typeOfCard = "WRITE A BOOK";
					description = "Got something to share to the world? Wanna earn good money? Why not BOTH?!";
					break;
				}
			}
			toDoAction = "Collect $30000 From The Bank";
		}
		else if (mainID == 1) { // Pay the Bank
				switch (subID) {
					case 0 : {
						typeOfCard = "BUY AN ITEM!";
						description = "Get ahold of the cool stuff! Got the money for it?";
						break;
					}
					case 1 : {
						typeOfCard = "VISIT A PLACE";
						description = "Go spend some good time in a good place!";
						break;
					}
					case 2 : {
						typeOfCard = "HIKING";
						description = "Climb to the top of the mountains and assert dominance!";
						break;
					}
					case 3 : {
						typeOfCard = "WATCH A SHOW";
						description = "Binge up on the latest shows!";
						break;
					}
					case 4 : {
						typeOfCard = "WIN A COMPETITION";
						description = "Put your skills to the test!";
						break;
					}
					case 5 : {
						typeOfCard = "TRAFFIC VIOLATION";
						description = "Oopsies! No escaping the law!";
						break;
					}
				}
				toDoAction = "Pay $30000 To The Bank";
		}
		else if (mainID == 2) { // Pay the Player
			switch (subID) {
				case 0 : {
					typeOfCard = "LAWSUIT";
					description = "Settle a case!";
					toDoAction = "Choose A Player, then Pay $15000";
					break;
				}
				case 1 : {
					typeOfCard = "CHRISTMAS BONUS";
					description = "Wish all a happy Merry Christmas!";
					toDoAction = "Pay $15000 To Each Players";
					break;
				}
			}
		}
		else { // Collect From Player
			switch (subID) {
				case 0 : {
					typeOfCard = "FILE A LAWSUIT!";
					description = "Bring them to court!";
					toDoAction = "Choose a player, then collect $5000";
					break;
				}
				case 1 : {
					typeOfCard = "IT'S YOUR BIRTHDAY!";
					description = "It's your day! Go celebrate your birthday with others!";
					toDoAction = "Collect $5000 From Each Players";
					break;
				}
			}
		}
	}
	
	public void doAction(Player[] players, int turn, int numberOfPlayersInGame) {
		switch (mainID) {
			case 0 : { // Collect from bank
				players[turn].addMoneyBalance(20000.00);
				System.out.println("$20000 added to Player " + (turn + 1));
				System.out.println("Updated MONEY: " + players[turn].getMoneyBalance());
				break;
			}
			case 1: { // Pay the bank
				players[turn].reduceMoneyBalance(20000.00);
				System.out.println("$20000 deducted from Player " + (turn + 1));
				System.out.println("Updated current MONEY: " + players[turn].getMoneyBalance());
				break;
			}
			case 2 : { // Pay the player
				if (subID == 0) { // Choose a player
					int playerNum;
					do {
						System.out.println("Choose player number to pay: ");
						playerNum = Integer.parseInt(input.nextLine());
						
						if (playerNum < 1 || playerNum > numberOfPlayersInGame)
							System.out.println("Oops wrong input!");
						
						else if (playerNum == (turn + 1))
							System.out.println("You cannot choose yourself!");
						
					} while (playerNum == (turn + 1) || (playerNum < 1 || playerNum > numberOfPlayersInGame));
					players[turn].reduceMoneyBalance(20000.00);
					players[playerNum - 1].addMoneyBalance(20000.00);
				}
				else { // Choose all players
					for (int i = 0; i <= numberOfPlayersInGame - 1; i++)
						if (i != turn) {
							players[turn].reduceMoneyBalance(20000.00);
							players[i].addMoneyBalance(20000.00);
							System.out.println("PAID Player " + (i + 1)+ " $20000.00");
						}
				}
				System.out.println("Updated current MONEY: $" + players[turn].getMoneyBalance());
				break;
			}
			case 3 : { // Collect from player
				if (subID == 0) { // Choose a player
					int playerNum;
					do {
						System.out.println("Choose Player number to collect: ");
						playerNum = Integer.parseInt(input.nextLine());
						
						if ((playerNum < 1 || playerNum > numberOfPlayersInGame))
							System.out.println("Oops wrong input!");
						
						else if (playerNum == (turn + 1))
							System.out.println("You cannot choose yourself!");
						
					} 	while (playerNum == (turn + 1) || (playerNum < 1 || playerNum > numberOfPlayersInGame));
					players[turn].addMoneyBalance(20000.00);
					players[playerNum - 1].reduceMoneyBalance(20000.00);
				}
				else { // Choose all players
					for (int i = 0; i <= numberOfPlayersInGame - 1; i++)
						if (i != turn) {
							players[turn].addMoneyBalance(20000.00);
							players[i].reduceMoneyBalance(20000.00);
							System.out.println("COLLECTED from Player " + (i + 1)+ " $20000.00");
						}
				}
				System.out.println("Updated current MONEY: $" + players[turn].getMoneyBalance());
				break;
			}
		}
	}
	
	public static ActionCard top(ArrayList<ActionCard> deck) { // return the top most card (this method is NOT YET USED)
		return deck.get(head);
	}
	
	public static ActionCard pop(ArrayList<ActionCard> deck) { // return the top most card, then shift head to next card.
		ActionCard temp = ActionCard.top(deck);
		head--;
		return temp;
	}
	
	public int getMainID() {
		return this.mainID;
	}
	
	public String getTypeOfCard() {
		return this.toDoAction;
	}

	@Override
	public String toString() { // Return card name and its description.
		return this.typeOfCard + "\n" + this.description + "\n";
	}
}
 


