import java.util.*;
import java.io.*;



public class BankProgram {

	/**
	 * @throws IOException 
	 * @throws NumberFormatException 
	 * @mparam args
	 */
	public static void main(String[] args) throws Exception, NumberFormatException, IOException
	{
		
		BufferedReader bufferReader=new BufferedReader(new InputStreamReader(System.in));
		int numberOfCustomer=0;
		Bank bank=new Bank();
		Customer[] c=bank.getCustomer();
		while(true)
		{
			System.out.println("Enter your choice : ");
			System.out.println("1: add customer .");
			System.out.println("2: deposit money.");
			System.out.println("3: withdraw money.");
			System.out.println("4: check balance.");
			System.out.println("5: calculate interest.");
			System.out.println("6. exit.");
			int choice=Integer.parseInt(bufferReader.readLine());
			switch(choice)
			{
			case 1:
				System.out.println("creating an account .....");
				System.out.println("enter amount : ");
				double bal=Double.parseDouble(bufferReader.readLine());
				System.out.println("enter account number : ");
				String acc=bufferReader.readLine();
				Account account = new Account(bal,acc);
				System.out.println("enter name : ");
				String name=bufferReader.readLine();
				Customer customer=new Customer(name,account);
				c[numberOfCustomer]=customer;
				numberOfCustomer++;
				System.err.println("NUmber of customers " + numberOfCustomer);
				for(int i=0;i<numberOfCustomer;i++)
				{
					c[i].display();
				}
						break;
			case 2:
				System.out.println("enter account number : ");
				 acc=bufferReader.readLine();
				
				if(numberOfCustomer==0)
				{
					System.out.println("account not found.");
				}
				else if(numberOfCustomer>0)
				{
					boolean found=false;
					for(int i=0;i<numberOfCustomer;i++)
					{
						Account temp=c[i].getAccount();
						String accTemp=temp.getAccountNumber();
						//System.out.println(accTemp);
						if(accTemp.equals(acc))
						{
						System.err.print("enter the amount to deposit :");
						double money = Double.parseDouble(bufferReader.readLine());
						temp.deposit(money);
						found=true;
						}
					}
					
						if(found==false)
						{
						System.err.print("account number not found");
						}
				}
				break;
			case 3:
				System.out.println("enter account number : ");
				 acc=bufferReader.readLine();
				
				if(numberOfCustomer==0)
				{
					System.out.println("account not found.");
				}
				else if(numberOfCustomer>0)
				{
					boolean found=false;
					for(int i=0;i<numberOfCustomer;i++)
					{
						Account temp=c[i].getAccount();
						String accTemp=temp.getAccountNumber();
						//System.out.println(accTemp);
						if(accTemp.equals(acc))
						{
						System.err.print("enter the amount to withdraw :");
						double money = Double.parseDouble(bufferReader.readLine());
						temp.withDraw(money);
						found=true;
						}
					}
					
						if(found==false)
						{
						System.err.print("account number not found");
						}
				}
				
				break;
			case 4:
				System.out.println("enter account number : ");
				 acc=bufferReader.readLine();
				
				if(numberOfCustomer==0)
				{
					System.out.println("account not found.");
				}
				else if(numberOfCustomer>0)
				{
					boolean found=false;
					for(int i=0;i<numberOfCustomer;i++)
					{
						Account temp=c[i].getAccount();
						String accTemp=temp.getAccountNumber();
						//System.out.println(accTemp);
						if(accTemp.equals(acc))
						{
						
						System.err.println("the balance is :\t"+temp.checkBalance()+"\n");
						found=true;
						}
					}
					
						if(found==false)
						{
						System.err.print("account number not found");
						}
				}
				
				
				break;
			case 5:
				System.out.println("enter account number : ");
				 acc=bufferReader.readLine();
				
				if(numberOfCustomer==0)
				{
					System.out.println("account not found.");
				}
				else if(numberOfCustomer>0)
				{
					boolean found=false;
					for(int i=0;i<numberOfCustomer;i++)
					{
						Account temp=c[i].getAccount();
						String accTemp=temp.getAccountNumber();
						//System.out.println(accTemp);
						if(accTemp.equals(acc))
						{
						bank.calculateInterest(c[i]);
						found=true;
						}
					}
					
						if(found==false)
						{
						System.err.print("account number not found");
						}
				}
				
				
				break;
			case 6:
				System.exit(0);
				break;
			
			}
		}

	}

}

class Bank{
	private double rateOfInterest=8.5;
	private double transactionFees=10;
	private Customer[] customers=new Customer[1000];
	
	public Customer[] getCustomer()
	{
		return customers;
	}
	public void calculateInterest(Customer customer)
	{
		Account a=customer.getAccount();
		double bal=a.checkBalance();
		double interestAmount=bal*rateOfInterest/100;
		double totalBalance=bal+interestAmount;
		System.out.println("intrest amount \t"+ interestAmount+"total money after ineterest : \t"+totalBalance);
	}
	public void getInterestRate()
	{}

	public double getTransactionFees()
	{
		return transactionFees;
	}
} 


class Account{
	private double balance=100;
	private String accountId;
	private boolean firstTime=true;
	
	public Account(String acc)
	{
		accountId = acc;
	}
	
	public Account(double bal,String acc)
	{
		if (bal>=100)
			{
			balance=bal;
			
			}
			else
			{
			System.err.println("cannot process,enter positive amount please");
			System.exit(0);
			}
		accountId=acc;
		
		}
	
	public void withDraw(double howMuch)
	{
		if (howMuch>=0)
		{
			if(firstTime==true)
			{
				double tempBalance=balance;
				tempBalance=tempBalance-howMuch;
				if(tempBalance>=100)
				{
					balance=balance-howMuch;
				}
				else
				{
					System.err.print("Insufficient balance!\n");
				}
				firstTime=false;
			}
			else
			{
				Bank bank=new Bank();
				double tempBalance=balance;
				tempBalance=tempBalance-howMuch-bank.getTransactionFees();
				if(tempBalance>=100)
				{
					balance=balance-howMuch-bank.getTransactionFees();
				}
				else
				{
					System.err.print("Insufficient balance!\n");
				}
				
			}
		}
	}
	public void deposit(double howMuch)
	{
		if(howMuch>0)
		{
			balance=balance+howMuch;
;
		}
		else
		{
			System.err.println("please enter a positive amount! ");
		}
	}
	public double checkBalance()
	{
		return balance;
	}
	public String getAccountNumber()
	{
		return accountId;
		
	}
}


class Customer
{
	private String name;
	private Account account;
	public void display()
	{
		System.out.print("Name : \n " + name+  "\n account number :"+account.getAccountNumber()+"\n");
	}
	public String getName() {
		return name;
	}
	public Account getAccount() {
		return account;
	}
	Customer(String n,Account a)
	{
		name=n;
		account = a;
	}
	
	
}