package test.pkg;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CountMaxToys {

	/**
	 * Toys class contains name, price and count available in a shop
	 *
	 */
	public class Toy {
		private String toyName;
		private Integer price;
		private Integer count;

		public Toy(String toyName, Integer price, Integer count) {
			super();
			this.toyName = toyName;
			this.price = price;
			this.count = count;
		}

		public String getToyName() {
			return toyName;
		}

		public void setToyName(String toyName) {
			this.toyName = toyName;
		}

		public Integer getPrice() {
			return price;
		}

		public void setPrice(Integer price) {
			this.price = price;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

	}

	/**
	 * Shop class contains shop name and list of Toys
	 *
	 */
	public class Shop {
		String name;
		List<Toy> toyList;

		public Shop(String name, List<Toy> toyList) {
			super();
			this.toyList = toyList;
		}

		public List<Toy> getToyList() {
			return toyList;
		}

		public void setToyList(List<Toy> toyList) {
			this.toyList = toyList;
		}

	}

	/**
	 * Setup initial data here
	 *
	 * This method will return Shop class.
	 *
	 */
	public Shop setupData() {

		Toy toy1 = new Toy("A", 5, 1);
		Toy toy2 = new Toy("B", 1, 10);
		Toy toy3 = new Toy("C", 3, 5);
		Toy toy4 = new Toy("D", 2, 3);
		Toy toy5 = new Toy("E", 8, 0);

		List<Toy> toyList = Arrays.asList(toy1, toy2, toy3, toy4, toy5);

		Shop shop = new Shop("S", toyList);
		return shop;
	}

	/**
	 * Method to count the maximum number of toys that can be bought
	 *
	 * @param toyList
	 * @param totalMoney
	 * @return count of toys
	 */
	public int getMaxToyCount(List<Toy> toyList, int totalMoney) {
		int output = 0;
		int moneyAvailable = totalMoney;

		// comparator to sort list of toys by price
		Comparator<Toy> com = Comparator.comparing(Toy::getPrice);

		// List of toys sorted by price
		List<Toy> toysSortedByPrice = toyList.stream().sorted(com).collect(Collectors.toList());

		for (Toy toy : toysSortedByPrice) {

			int toysCount = toy.getCount();

			while (toysCount > 0) {

				// if the available money is less than the toy price return
				if (moneyAvailable < toy.getPrice()) {
					return output;
				}
				// deduct the money from available money, reduce the current toy count and
				// increase the toy count that can be bought
				moneyAvailable = moneyAvailable - toy.getPrice();
				toysCount--;
				output++;
			}
		}
		return output;
	}

	public static void main(String[] args) {
		CountMaxToys test = new CountMaxToys();

		Shop shop = test.setupData();
		int totalMoney = 25;

		System.out.println(test.getMaxToyCount(shop.getToyList(), totalMoney));

	}

}
