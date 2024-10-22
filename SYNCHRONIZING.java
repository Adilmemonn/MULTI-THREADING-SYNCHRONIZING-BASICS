class Bank
{
public int balance;
public Bank(int balance)
{
this.balance=balance;
}

public synchronized void withdraw(int wamt)
{
if(wamt>this.balance)
{
System.out.println("Current Balance : " + this.balance);
System.out.println("Insufficient Funds, cannot withdraw" + wamt);
try{
	wait();
	}
	catch(Exception e){}
}
this.balance-=wamt;
System.out.println("Withdrawl Success :"); 
System.out.println("Current Balance : " + this.balance);
}

public synchronized void deposit(int damt)
{
	try{
	Thread.sleep(5000);
	}
	catch(Exception e){}
	
	this.balance+=damt;
	notify();
}
}

class Son extends Thread
{
Bank bank;
public Son(Bank bank)
{
this.bank=bank;
}

public void run()
{
bank.withdraw(10000);
}
}


class Father extends Thread
{
Bank bank;
public Father(Bank bank)
{
this.bank=bank;
}

public void run()
{
bank.deposit(50000);
}
}

public class DriverApp
{
public static void main(String args[])
{
Bank bank=new Bank(5000);
Son thread1=new Son(bank);
Father thread2=new Father(bank);
thread1.start();
thread2.start();
}
}

