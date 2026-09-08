public class Laptop extends Computer {
    public int batareyCharge;

    public Laptop(String cpu, int ram) {
        super(cpu, ram);
        batareyCharge = 100;
    }

    public void charge() {
        batareyCharge = 100;
    }

    @Override
    public void runProgram(String programName) {
        if (batareyCharge <= 0) {
            System.out.println("Не удалось запустить \"" + programName + "\": батарея разряжена!");
            return;
        }

        super.runProgram(programName);
        batareyCharge -= 10;

        if (batareyCharge < 0) {
            batareyCharge = 0;
        }
    }
}
