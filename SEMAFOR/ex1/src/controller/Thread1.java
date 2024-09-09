package controller;

import java.util.concurrent.Semaphore;

public class Thread1 extends Thread
{

	private int TID;
	private Semaphore semaforo;
	
	
	public Thread1(int TID, Semaphore semaforo) 
	{
		this.TID = TID;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() 
	{
		
		switch (TID % 3)
		{
		case 1:
			Executa(2, 0.2, 1.0, 1.0); //qnts vezes vai ser executado o ciclo "calculo" - "transação" / tempo min. calculo (s)/ tempo max. calculo / tempo transação (s)
			break;
		case 2:
			Executa(3, 0.5, 1.5, 1.5); //qnts vezes vai ser executado o ciclo "calculo" - "transação" / tempo min. calculo (s)/ tempo max. calculo / tempo transação (s)
			break;
		case 0:
			Executa(3, 1.0, 2.0, 1.5); //qnts vezes vai ser executado o ciclo "calculo" - "transação" / tempo min. calculo (s)/ tempo max. calculo / tempo transação (s)
			break;
		}
	}
	
	private void Executa(int xCiclo, double mincalc, double maxcalc, double ttransacao)
	{
		while (xCiclo > 0)
		{
			int c = Calculos(mincalc, maxcalc);
			System.out.println("Thread #" + TID + "Fez calculo");
			
			try 
			{
				semaforo.acquire();
				System.out.println("Thread #" + TID + "estrou na fila");
				Transacao(ttransacao, c);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			finally 
			{
				semaforo.release();	
				System.out.println("Thread #" + TID + "Saiu da fila");

			}
			System.out.println("Thread #" + TID + "Fez a transação");

			xCiclo--;
		}
	}
		
	private int Calculos(double mincalc, double maxcalc) 
	{
		
		try 
		{
		sleep((long)((Math.random() * ((maxcalc - mincalc) * 1000)) + (1000*mincalc)));
		}
		catch(InterruptedException e) 
		{
		System.err.println(e.getMessage());	
		}
		
		return (int)(Math.random() * 10);
	}
	
	private void Transacao(double ttransacao, int c) 
	{
		System.out.println(c);
		try 
		{
		sleep((long)(ttransacao * 1000));
		}
		catch(InterruptedException e) 
		{
		System.err.println(e.getMessage());	
		}
	}
	
	
	
	
	
}
