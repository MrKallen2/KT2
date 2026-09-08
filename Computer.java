public class Computer {
    private String cpu;
    private int ram;

    public Computer(String cpu, int ram) {
        this.cpu = cpu;
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public int getRam() {
        return ram;
    }

    public void runProgram(String programName) {
        System.out.println("Запуск программы \"" + programName + "\" (CPU: " + cpu + ", RAM: " + ram + " ГБ)");
    }
}
