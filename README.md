public class Computer {
    private String operatingSystem;
    private String cpu;
    private int ram; // в гигабайтах

    public Computer(String operatingSystem, String cpu, int ram) {
        this.operatingSystem = operatingSystem;
        this.cpu = cpu;
        this.ram = ram;
    }

    // Геттеры и сеттеры
    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    // Метод для запуска программ
    public void runProgramm(String programName) {
        System.out.println("Запуск программы \"" + programName + "\" на компьютере с ОС "
                + operatingSystem + ", CPU: " + cpu + ", RAM: " + ram + " ГБ");
    }

    public static void main(String[] args) {
        Computer myComputer = new Computer("Windows 11", "Intel Core i7", 16);
        myComputer.runProgramm("Google Chrome");
    }
}
