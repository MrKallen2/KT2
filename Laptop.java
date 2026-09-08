public class Laptop extends Computer {
    private int batteryPercent;

    public Laptop(String operatingSystem, String cpu, int ram, int batteryPercent) {
        super(operatingSystem, cpu, ram); // вызываем конструктор родителя
        this.batteryPercent = batteryPercent;
    }

    public int getBatteryPercent() {
        return batteryPercent;
    }

    public void setBatteryPercent(int batteryPercent) {
        if (batteryPercent < 0) {
            this.batteryPercent = 0;
        } else if (batteryPercent > 100) {
            this.batteryPercent = 100;
        } else {
            this.batteryPercent = batteryPercent;
        }
    }

    // Метод для зарядки ноутбука
    public void charge() {
        batteryPercent = 100;
        System.out.println("Ноутбук заряжен. Заряд батареи: " + batteryPercent + "%");
    }

    // Переопределяем метод запуска программ — он тратит заряд батареи
    @Override
    public void runProgramm(String programName) {
        if (batteryPercent <= 0) {
            System.out.println("Не удалось запустить \"" + programName + "\": батарея разряжена!");
            return;
        }

        super.runProgramm(programName); // вызываем логику родительского метода
        batteryPercent -= 10; // расход заряда за запуск программы

        if (batteryPercent < 0) {
            batteryPercent = 0;
        }

        System.out.println("Осталось заряда: " + batteryPercent + "%");
    }

    public static void main(String[] args) {
        Laptop myLaptop = new Laptop("Windows 11", "Intel Core i7", 16, 25);

        myLaptop.runProgramm("Google Chrome");
        myLaptop.runProgramm("IntelliJ IDEA");
        myLaptop.runProgramm("Photoshop");

        myLaptop.charge();
        myLaptop.runProgramm("Zoom");
    }
}
