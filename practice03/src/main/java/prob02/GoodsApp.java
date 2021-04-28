package prob02;

import java.util.Scanner;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;

	public static class Goods{
		private String product;
		private int price;
		private int stock;

		public Goods(String product, int price, int stock) {
			this.product = product;
			this.price = price;
			this.stock = stock;
		}
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];

		// 상품 입력
		for (int i = 0; i < goods.length; i++) {
			String product = scanner.next();
			int price = scanner.nextInt();
			int stock = scanner.nextInt();
			goods[i] = new Goods(product, price, stock);
		}


		// 상품 출
		for (int i = 0; i < goods.length; i++) {
			System.out.println(goods[i].product+"(가격:"+goods[i].price +"원)이 " +goods[i].stock + "개 입고 되었습니다.");
		}

		// 자원정리
		scanner.close();
	}
}
