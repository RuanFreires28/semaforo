package view;

import java.util.concurrent.Semaphore;
import controller.ThreadOvercooked;

public class MainOvercooked 
{
	public static void main(String[] args) 
	{
		
		Semaphore semaforo = new Semaphore(5);
		Semaphore semaforo2 = new Semaphore(1);
		
		for (int TID = 0; TID < 10; TID++)
		{
			ThreadOvercooked t = new ThreadOvercooked(TID, semaforo, semaforo2);
			
			t.start();
		}
	}
}
