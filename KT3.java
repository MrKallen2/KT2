import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

abstract class Task {
    private static int nextId = 1; 
    protected int id;
    protected String title;
    protected String description;

    public Task(String title, String description) {
        this.id = nextId++; 
        this.title = title;
        this.description = description;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }

    public abstract String getTaskType();

    @Override
    public String toString() {
        return String.format("[%d] %s | %s | Тип: %s", id, title, description, getTaskType());
    }
}

class DeadlineTask extends Task {
    private LocalDate deadline;

    public DeadlineTask(String title, String description, LocalDate deadline) {
        super(title, description);
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "С дедлайном (" + deadline.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ")";
    }
}

class UsualTask extends Task {
    private String frequency;

    public UsualTask(String title, String description, String frequency) {
        super(title, description);
        this.frequency = frequency;
    }

    @Override
    public String getTaskType() {
        return "Частая задача (Повтор: " + frequency + ")";
    }
}

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Задача добавлена.");
    }

    public void removeTask(int taskId) {
        boolean removed = tasks.removeIf(task -> task.getId() == taskId);
        if (removed) {
            System.out.println("Задача с ID " + taskId + " удалена.");
        } else {
            System.out.println("Задача с ID " + taskId + " не найдена.");
        }
    }

    public void showAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст.");
            return;
        }
        System.out.println("--- Список задач ---");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
        System.out.println("--------------------");
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        manager.addTask(new DeadlineTask("Сдать проект", "Загрузить на сервер", LocalDate.of(2026, 9, 15)));
        manager.addTask(new UsualTask("Поливать цветы", "Кактус в офисе", "Раз в неделю"));
        manager.addTask(new DeadlineTask("Оплатить интернет", "Квитанция на почте", LocalDate.of(2026, 9, 10)));

        manager.showAllTasks();
        
        manager.removeTask(2); 
        manager.showAllTasks();
    }
}  