public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer("intel core i7", 32);
        computer.runProgram("vs code"); // Запуск vs code

        // Изначально батарея ноутбука на 100%
        Laptop laptop = new Laptop("intel core i5", 16);
        System.out.println(laptop.batareyCharge); // 100
        laptop.runProgram("pycharm"); // Запуск pycharm
        System.out.println(laptop.batareyCharge); // 90
        laptop.charge(); // Батарея заряжена до 100
    }
}
