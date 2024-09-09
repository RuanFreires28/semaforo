package view;

import controller.Thread1;
import java.util.concurrent.Semaphore;

public class Main1 
{
	public static void main(String[] args) 
	{
	
		Semaphore semaforo = new Semaphore(1);
		
		for (int TID = 0; TID < 10; TID++)
		{
			Thread1 t = new Thread1(TID, semaforo);
			t.start();
		}
		
	}
}
