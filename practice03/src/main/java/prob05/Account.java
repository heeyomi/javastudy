package prob05;

public class Account {
	private String accountNo;
	private int balance;
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public Account(String accountNo) {
		this(accountNo, 0);
	}
	
	public Account(String accountNo, int balance) {
		this.accountNo = accountNo;
		this.balance = balance;
		System.out.println(accountNo + "계좌가 개설되었습니다.");
	}

	public void save(int money) {
		this.balance += money;
		System.out.println(accountNo + "계좌에 " + money + "만원이 입금되었습니다." );
	}
	
	public void deposit(int deposit) {
		this.balance -= deposit;
		System.out.println(accountNo + "계좌에 " + deposit + "만원이 출금되었습니다." );
	}
	
}
