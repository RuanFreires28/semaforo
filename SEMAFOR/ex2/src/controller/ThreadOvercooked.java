package controller;

import java.util.concurrent.Semaphore;

public class ThreadOvercooked extends Thread
{

	private int TID;
	private Semaphore semaforo;
	private Semaphore semaforo2;
	private String nome = "";
	
	public ThreadOvercooked(int _TID, Semaphore _semaforo, Semaphore _semaforo2)
	{
		TID = _TID;
		semaforo = _semaforo;
		semaforo2 = _semaforo2;
	}
	
	@Override
	public void run() 
	{
		double tmax = 0.0;
		double tmin = 0.0;
		
		switch (TID % 2)
		{
		 case 1:
			nome = "Sopa de Cebola"; 
			tmin = 0.5;
			tmax = 0.8;
			break;
		 case 0:
			nome = "Lasanha a Bolonhesa";
			tmin = 0.6;
			tmax = 1.2; 
			break;
		}
		 
		try 
		{
			semaforo.acquire();
			cozimento(tmin, tmax);
		}
		 catch (InterruptedException e)
		{
			System.err.println(e.getMessage());
		}
		 finally 
		{
			 semaforo.release();
		}
		
		
		try 
		{
			semaforo2.acquire();
			entrega();
		}
		 catch (InterruptedException e)
		{
			 System.err.println(e.getMessage());;
		}
		 finally 
		{
			 semaforo2.release();
		}
		
	}

	private void cozimento(double tmin, double tmax) 
	{
		double porcentagem = 0;
		double tempo = ((Math.random() * ((tmax - tmin))) + tmin);
		System.out.println("o prato #" + TID + " de " + nome + " vai iniciar seu cozimento");
		
		double copytempo = tempo;
		
		while (tempo>0)
		{				
			try 
			{	
				sleep(100);
			} 
			catch (InterruptedException e) 
			{
				System.err.println(e.getMessage());	
			}
			porcentagem = porcentagem + (0.1/(copytempo));
			System.out.println("o prato #" + TID + " de " + nome + " ja foi " + porcentagem + "% cozido");
			tempo = tempo - 0.1	;
		}
		
		System.out.println("o prato #" + TID + " de " + nome + " está pronto");

	}
	
	
	private void entrega() 
	{
		System.out.println("o prato #" + TID + " de " + nome + " vai ser entregue");
		
		try 
		{
		sleep(500);			
		} 
		catch (InterruptedException e) 
		{
			System.err.println(e.getMessage());
		}
		
		System.out.println("o prato #" + TID +" de " + nome +  " foi entregue");
		
	}
}