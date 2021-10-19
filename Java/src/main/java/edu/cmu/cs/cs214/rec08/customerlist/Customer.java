package edu.cmu.cs.cs214.rec08.customerlist;

import java.util.Objects;

/**
 * A customer in our business, who has a name, email, and address.
 */
public final class Customer {
	private final String name;
	private final String emailAddress;
	private String address;

	public Customer(String name, String emailAddress, String address) {
		this.name = Objects.requireNonNull(name);
		this.emailAddress = Objects.requireNonNull(emailAddress);
		this.address = Objects.requireNonNull(address);
	}

	public String getName() {
		return name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Customer customer = (Customer) o;

		if (!name.equals(customer.name)) return false;
		if (!emailAddress.equals(customer.emailAddress)) return false;
		return address.equals(customer.address);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + emailAddress.hashCode();
		result = 31 * result + address.hashCode();
		return result;
	}
}
