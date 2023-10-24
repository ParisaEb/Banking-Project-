package com.example.TheBankingApp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@Column(nullable = false)
	private int accountNum;
	@Column(nullable = true)
	private double balance;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Account() {

	}

	public Account(int accountNum, User user) {
		this.accountNum = accountNum;
		this.user = user;
	}

	public User getClient() {
		return user;
	}

	public void setClient(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Account [accountNum=" + accountNum + ", balance=" + balance + ", client=" + user + "]";
	}

	public int getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getCustomer() {
		return user;
	}

	public void setCustomer(User user) {
		this.user = user;
	}
	
}
