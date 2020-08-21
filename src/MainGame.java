import java.util.*;
public class MainGame {
	private int numberOfPlayersInGame;
	private ArrayList<ActionCard> actionCards;
	private Player[] players;
	int turn;

	private Scanner input = new Scanner(System.in);
	
	/* This is what executes at the start of the program.
	 * First, a deck of Action Cards is generated.
	 * Next, the program asks the user how many players are there (2-3), then uses that to instantiate player array.
	 * Each player's career will be assigned as Athlete (for now)
	 * 
	 * After this, the first round starts at phase1Game() method.
	 */
	public void preliminaryStart() {
		actionCards = new ArrayList<ActionCard>();
		turn = 0;
		
		generateDeckOfActionCards(actionCards);
		
		do {
			System.out.print("Enter number of players: ");
			numberOfPlayersInGame = Integer.parseInt(input.nextLine());
			players = new Player[numberOfPlayersInGame];
			
			if (numberOfPlayersInGame < 1 || numberOfPlayersInGame > 3)
			 System.out.println("Error!");
			else if(numberOfPlayersInGame == 1)
				System.out.println("The game is NOT designed for single player.");
			
		} while (numberOfPlayersInGame < 2 || numberOfPlayersInGame > 3);
		
		for (int i = 0; i < numberOfPlayersInGame; i++)
			players[i] = new Player((i+1));

		
		System.out.println("Notice: Career assigned to each player is Athlete for now.");
		for (int i = 0; i < numberOfPlayersInGame; i++)
			players[i].setCareer("Athlete");
	}
	
	public void generateDeckOfActionCards(ArrayList<ActionCard> actionCards) {
		int i;
		for (i = 0; i < 20; i++) {
			actionCards.add(new ActionCard(0)); // create cards with ID 0 (collect from bank)
			actionCards.get(i).generateSubID(); // See ActionCard class
			actionCards.get(i).assignDescriptions(actionCards.get(i).mainID);
		}
		for (i = 20; i < 40; i++) {
			actionCards.add(new ActionCard(1)); // create cards with ID 1 (pay the bank)
			actionCards.get(i).generateSubID();
			actionCards.get(i).assignDescriptions(actionCards.get(i).mainID);
		}
		for (i = 40; i < 45; i++) {
			actionCards.add(new ActionCard(2)); // ID 2 (pay the player)
			actionCards.get(i).generateSubID();
			actionCards.get(i).assignDescriptions(actionCards.get(i).mainID);
		}
		for (i = 45; i < 50; i++) {
			actionCards.add(new ActionCard(3)); // ID 3 (collect from player)
			actionCards.get(i).generateSubID();
			actionCards.get(i).assignDescriptions(actionCards.get(i).mainID);
		}
		
		Collections.shuffle(actionCards);
	}
	
	public void displayActionCards(ArrayList<ActionCard> deck) {
		System.out.println("Action Cards generated (uses stack implementation):");
		for (ActionCard card : deck)
			System.out.print(card.typeOfCard + ", ");
		System.out.println("\n");
	}
	
	/*	First, show the current player's status.
	 * 	Then draw a card. (Uses stack implementation, head starts at the last card (index 49)
	 * 	Show card details.
	 * 	Execute the action of the card.
	 */
	private void phase1Game(int turn) {
		System.out.println("PLAYER " + (turn+1) 
				+ "'s turn! | MONEY: " + this.players[turn].getMoneyBalance() + " | LOAN: " + this.players[turn].getMoneyLoan());
		
		ActionCard drawn = this.drawDeck();
		
		System.out.println("Action card drawn: " + drawn.toString());
		
		drawn.doAction(this.players, turn, numberOfPlayersInGame);
	}
	
	public ActionCard drawDeck() {
		return ActionCard.pop(this.actionCards);
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome! This is an initial build of the \"That's Life!\" project. "
				+ "More features to come in the final buld.\n");

		MainGame game = new MainGame(); // Create game instance.
		
		game.preliminaryStart();
		game.displayActionCards(game.actionCards);
		
		do {
			String answer; // This is just for user confirmation if he wants to continue/exit the game.
			
			game.phase1Game(game.turn); // The turn variable is for tracking who's player turn it is (Current player number - 1) e.g Player 1's turn -> turn = 0
			
			System.out.println("Continue playing? Y/N: ");
			answer = game.input.nextLine();
			
			if (answer.equalsIgnoreCase("N"))
				break;
			
			game.turn = (game.turn == (game.numberOfPlayersInGame - 1)) ? 0 : game.turn + 1;
			System.out.println("--------");
		} while (true);
		
	}

}
