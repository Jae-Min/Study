package thirdwork;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Stream;

import secondwork.TelephoneDirectoryDTO;

public class TelephoneDirectory {
	
	// 회원 정보를 저장할 list 컬렉션 
	static List<TelephoneDirectoryDTO> list = new Vector<TelephoneDirectoryDTO>();
	static Scanner scanner = new Scanner(System.in);
	
	// 메뉴 생성
	private static void createMenu() {
		
		System.out.println("==========[Main Menu]==========");
		System.out.println("       1. 조회     |  4. 삭제");
		System.out.println("       2. 입력     |  5. 종료");
		System.out.println("       3. 수정     |");
		System.out.println("===============================");
		System.out.print("메뉴 번호를 입력하세요 : ");
		
	}
	
	// 메뉴 선택에 따른 분기
	private static void selectMenu() {
		
		String menu = null;
		
		while(true) {
			
			createMenu();
			menu = scanner.nextLine();
			
			switch (menu) {
				case "1": // 조회
					createSearchMenu();
					showSearchResult();
					break;
				case "2": // 입력
					insert();
					break;
				case "3": // 수정
					createUpdateMenu();
					update();
					break;
				case "4": // 삭제
					delete();
					break;
				case "5":
					System.out.println("프로그램을 종료합니다");
					System.exit(0);
				default : 
					System.out.println("존재하지 않는 메뉴입니다");
			}
			
		}
		
	}
	
	// 조회 메뉴 선택 시 서브 메뉴 생성
	private static void createSearchMenu() {
		
		System.out.println("==============[Sub Menu]==============");
		System.out.println("1. 전체조회      2. 이름으로 조회      3. 연락처로 조회");
		System.out.println("======================================");
		System.out.print("메뉴번호를 입력하세요 : ");
	}
	
	// 조회 결과 출력
	private static void showSearchResult() {
		
		Stream<TelephoneDirectoryDTO> stream = list.stream();
		String menu = scanner.nextLine();
		boolean flag = false;
		
		switch (menu) {
			case "1":
				
				stream.forEach(dto -> System.out.println(String.format("이름 : %s , 연락처 : %s", dto.getName(), dto.getPhone())));
				flag = true;
				break;
				
			case "2":
				System.out.print("조회할 이름을 입력하세요 : ");
				String name = scanner.nextLine();
				
				stream.forEach(dto -> {
					if(name.trim().contains(dto.getName())) {
						System.out.println(String.format("이름 : %s , 연락처 : %s ", dto.getName(), dto.getPhone()));
					}
				});
				
				break;
				
			case "3":
				System.out.print("조회할 연락처를 입력하세요 : ");
				String phone = scanner.nextLine();
				
				stream.forEach(dto -> {
					if(phone.trim().contains(dto.getPhone())) {
						System.out.println(String.format("이름 : %s , 연락처 : %s ", dto.getName(), dto.getPhone()));
					}
				});
				
				break;
			default : 
				System.out.println("존재하지 않는 메뉴입니다");
				return;
		}
		
		
	}
	
	// 정보 입력
	private static void insert() {
		
		TelephoneDirectoryDTO dto = new TelephoneDirectoryDTO();
		
		System.out.print("이름을 입력하세요 : ");
		String name = scanner.nextLine();
		
		System.out.print("연락처를 입력하세요( - 생략) : ");
		String phone = scanner.nextLine();
		
		dto.setName(name);
		dto.setPhone(phone);
		
		list.add(dto);
	}
	
	// 수정 메뉴 선택 시 서브 메뉴 생성
	private static void createUpdateMenu() {
		
		System.out.println("==============[Sub Menu]==============");
		System.out.println("1. 이름 수정      2. 연락처 수정");
		System.out.println("======================================");
		System.out.print("메뉴번호를 입력하세요 : ");
	}
	
	// 정보 수정
	private static void update() {

		String menu = scanner.nextLine();
		
		Stream<TelephoneDirectoryDTO> stream = list.stream();
		
		switch (menu) {
		
			case "1":
				System.out.print("수정할 대상의 이름을 입력하세요 : ");
				String inputName =  scanner.nextLine();
				
				stream.forEach(dto -> {
					if(inputName.trim().equals(dto.getName())) {
						System.out.print("이름 : "+ dto.getName() + " , 연락처 : " + dto.getPhone() + " 정보를 수정하시겠습니까?(y/n) : ");
						String inner_flag = scanner.nextLine();
						if(inner_flag.toLowerCase().trim().equals("y")) {
							System.out.print(dto.getName()+"의 이름 수정 : ");
							String name = scanner.nextLine();
							dto.setName(name);
						}
					}
				});
				break;
				
			case "2":
				System.out.print("수정할 대상의 연락처를 입력하세요 : ");
				String inputPhone =  scanner.nextLine();
				
				stream.forEach(dto -> {
					if(inputPhone.trim().equals(dto.getPhone())) {
						System.out.print("이름 : "+ dto.getName() + " , 연락처 : " + dto.getPhone() + " 정보를 수정하시겠습니까?(y/n) : ");
						String inner_flag = scanner.nextLine();
						if(inner_flag.toLowerCase().trim().equals("y")) {
							System.out.print(dto.getPhone()+"의 연락처 수정 : ");
							String phone = scanner.nextLine();
							dto.setPhone(phone);
						}
					}
				});
				break;
	
			default:
				System.out.println("존재하지 않는 메뉴입니다");
				return;
		}
	}
	
	// 정보 삭제
	/*
	 * 삭제 관련 메소드 또한 stream을 적용하려 했으나,
	 * List에 저장되어 있는 데이터 삭제 시 iterator를 사용할 경우 에러가 없지만 
	 * stream을 적용시 list.remove(dto); 이 같은 방식으로 List에 저장된 데이터를 삭제해야 하는데 
	 * 이 경우 에러가 발생하여 stream을 적용할 수 없었습니다. 
	 * 
	 */
	private static void delete() {
		System.out.print("삭제할 대상의 이름 혹은 연락처를 입력하세요 : ");
		String inputData =  scanner.nextLine();
		boolean flag = false;
		
		Iterator<TelephoneDirectoryDTO> iterator = list.iterator();
		while(iterator.hasNext()) {
			TelephoneDirectoryDTO dto = iterator.next();
			String name = dto.getName();
			String phone = dto.getPhone();
			
			if(inputData.trim().equals(name) || inputData.trim().equals(phone)) {
				
				System.out.print("이름 : "+ name + " , 연락처 : " + phone + " 정보를 삭제하시겠습니까?(y/n) : ");
				String innerFlag = scanner.nextLine();
				
				if(innerFlag.toLowerCase().trim().equals("y")) {
					System.out.println(String.format("이름 : %s , 연락처 : %s 의 정보가 삭제되었습니다", name, phone));
					iterator.remove();
					flag = true;
					break;
				} 
				flag = true;
			}
		}
		
		if(!flag) {
			System.out.println("삭제할 대상이 존재하지 않습니다");
		}
	}

	public static void main(String[] args) {
		selectMenu();
	}

}
