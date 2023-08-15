package shopping;

import java.util.Scanner;

public class shoppingSystemMain {
	
	// 키보드 입력
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		while(true) {
			// 메뉴출력
			System.out.println(Color.CYAN_BACK +"-------------------------- MENU --------------------------"+ Color.EXIT);
			System.out.println(Color.CYAN_BACK +"  1.상품입력 | 2.상품조회 | 3.상품수정 | 4.상품삭제 | 5.프로그램종료  "+ Color.EXIT);
			System.out.println(Color.CYAN_BACK +"----------------------------------------------------------"+ Color.EXIT);
			System.out.print(Color.CYAN +"메뉴를 선택하세요 >> "+ Color.EXIT);
			int keyInput = scan.nextInt();
			scan.nextLine();
			
			switch(keyInput) {
			case 1:
				System.out.println(Color.GREEN +"******************** 상품입력 ********************"+ Color.EXIT);
				new InsertShop().execute();
				System.out.println(Color.GREEN +"***********************************************"+ Color.EXIT);
				break;
			case 2:
				System.out.println(Color.GREEN +"******************** 상품조회 ********************"+ Color.EXIT);
				new SelectShop().execute();
				System.out.println(Color.GREEN +"***********************************************"+ Color.EXIT);
				break;
			case 3:
				System.out.println(Color.GREEN +"******************** 상품수정 ********************"+ Color.EXIT);
				new UpdateShop().execute();
				System.out.println(Color.GREEN +"***********************************************"+ Color.EXIT);
				break;
			case 4:
				System.out.println(Color.GREEN +"******************** 상품삭제 ********************"+ Color.EXIT);
				new DeleteShop().execute();
				System.out.println(Color.GREEN +"***********************************************"+ Color.EXIT);
				break;
			case 5:
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println(Color.RED +"메뉴를 다시 입력해주세요"+ Color.EXIT);
			}
		}
	}

}
