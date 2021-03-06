package edu.cmu.cs.cs214.rec08.customerlist;

import javax.swing.SwingUtilities;

import edu.cmu.cs.cs214.rec08.customerlist.gui.CustomerManagementUI;

/**
 * The main point of entry into our customer management system.
 */
public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(Main::createAndShowCustomerList);
	}

	public static void createAndShowCustomerList() {
		// Create a new instance of the data structure maintaining customers
		CustomerList customerList = new CustomerList();

		// Create a new instance of our ui, passing on the data structure we
		// created
		CustomerManagementUI ui = new CustomerManagementUI(customerList);

		// This handler sends a welcome email when a new customer is added
		CustomerEventHandler marketingHandler = new MarketingLetters();

		// This handler adds the new customer to our list of names on our
		// management panel. Note the argument passed to the constructor.
		CustomerEventHandler uiHandler = new CustomerListListener(
				ui.getManagementPanel());

		// Add the handlers we made.
		customerList.addHandler(marketingHandler);
		customerList.addHandler(uiHandler);

		// Show the UI.
		ui.show();
	}
}
