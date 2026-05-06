package segnalazioni;

public class Segnalazioni {

	public static void main(String[] args) {
		new Menu(new ComuneService(args[0])).start();
	}
}