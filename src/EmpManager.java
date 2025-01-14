import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmpManager {

    private List<Employee> employees = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    public void start() {
        employees.add(new Employee("홍길동", 1000, 2000));
        employees.add(new Developer("이순신", 1001, 3000, "C++"));
        employees.add(new Manager("강감찬", 1002, 3500, 10));

        System.out.println("직원 관리 프로그램 시작");

        while (running) {
            showMenu();
            try {
                String input = scanner.nextLine();  // 공백 입력 할 소지가 있다.
                if(input.trim().isEmpty()){
                    throw new NumberFormatException("빈 값은 허용하지 않습니다.");
                }
                int choice = Integer.parseInt(input); // 예외 발생

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        listEmployee();
                        break;
                    case 3:
                        increaseSalary();
                        break;
                    case 4:
                        System.out.println("시스템을 종료 합니다");
                        running = false;
                        continue;

                    default:
                        System.out.println("다시 입력 하시오.");
                }

            }catch (NumberFormatException e){
                e.printStackTrace();
            }catch (MyException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void showMenu() {
        // 15버전 이상
        System.out.println("""
                \n Menu :
                1. 직원 등록
                2. 직원 목록보기
                3. 실적 등록
                4. 종료
                메뉴를 선택 하시오.:
                """);

    }

    void addEmployee() {
        System.out.println("직원 등록 메뉴");
        System.out.println("""
                등록 직원 종류 선택 :
                1. 관리자
                2. 개발자
                """);
        int type = scanner.nextInt();
        scanner.nextLine();
        System.out.print("이름 :");
        String name = scanner.nextLine();
        System.out.print("ID :");
        int id = scanner.nextInt();
        System.out.print("급여 :");
        int salary = scanner.nextInt();
        if (type == 1) {
            System.out.print("팀 규모? ");
            int teamSize = scanner.nextInt();
            employees.add(new Manager(name, id, salary, teamSize));

        } else if (type == 2) {
            System.out.print("주 사용 언어? ");
            scanner.nextLine(); // 기존 입력된 값 쓰레기값 비우기
            String language = scanner.nextLine();
            employees.add(new Developer(name, id, salary, language));

        } else {
            System.out.println("잘못된 선택 입니다.");
        }

    }

    void listEmployee() {
        System.out.println("직원 목록 보기");
        System.out.println("======================================================");
        System.out.println("ID         이름                 급여       팀규모or언어");
        System.out.println("------------------------------------------------------");

        if (employees.isEmpty()) {
            System.out.println("등록된 직원이 없습니다.");
        } else {
            for (Employee emp : employees) {
                emp.introduce();
            }
        }
    }

    void increaseSalary() throws MyException {
        System.out.println("급여 인상 등록");
        System.out.print("직원 ID:");
        int id = scanner.nextInt();
        Employee target = null;
        for (Employee emp : employees) {
            if (emp.id == id) {
                target = emp;
                break;
            }
        }
        if (target != null) {
            System.out.println("""
                    1. 고정값으로 증가
                    2. 퍼센트로 증가
                    방식을 선택 :
                    """);
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.print("증가 급여 입력:");
                int amount = scanner.nextInt();
                target.increaseSalary(amount);
                System.out.println("급여 증가 성공");
            } else if (choice == 2) {
                System.out.print("증가 급여 퍼센트 입력:");
                double percentage = scanner.nextDouble();
                target.increaseSalary(percentage, true);
                System.out.println("급여 증가 퍼센트 적용");
            } else {
                System.out.println("다시 입력시오.");
            }

        } else {
            System.out.println("찾는 직웝이 없습니다.");
        }

    }
}
