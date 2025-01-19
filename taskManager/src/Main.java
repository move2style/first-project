import manager.TaskManager;
import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

public class Main {
    public static void main(String[] args) {
    TaskManager taskManager = new TaskManager();




        Task newtask1 = new Task("Zadacha po spisky#1", "opisanie", TaskStatus.NEW);
        taskManager.createTask(newtask1);
        Task newtask2 = new Task("Zadacha po spisky#2", "opisanie",TaskStatus.NEW);
        taskManager.createTask(newtask2);
        Task newtask1_1 = new Task("Zadacha po spisky#1_1", "opisanie", TaskStatus.IN_PROGRESS,1);
         taskManager.updateTask(newtask1_1);


        Epic newepic1 = new Epic("Epic po spisky#1", "opisanie",TaskStatus.NEW);
        taskManager.createEpic(newepic1);

        //меняем статус у эпика с айди 3
        Epic newepic1_1 = new Epic("Epic po spisky#1", "opisanie",TaskStatus.IN_PROGRESS,3);
        taskManager.updateEpic(newepic1_1);
        //меняем у эпика с айди 3 имя
        Epic newepic1_2 = new Epic("Epic po spisky#1_1", "opisanie",TaskStatus.NEW,3);
        taskManager.updateEpic(newepic1_2);

        Epic newepic2 = new Epic("Epic po spisky#2", "opisanie",TaskStatus.NEW);
        taskManager.createEpic(newepic2);


        Subtask newsubtask1 = new Subtask("Subtask #1", "opisanie",TaskStatus.NEW,newepic1.getId());
        Subtask newsubtask2 = new Subtask("Subtask #2", "opisanie",TaskStatus.NEW,newepic1.getId());
        Subtask newsubtask3 = new Subtask("Subtask #3", "opisanie",TaskStatus.NEW,newepic1.getId());
        taskManager.createSubtask(newsubtask1);
        taskManager.createSubtask(newsubtask2);
        taskManager.createSubtask(newsubtask3);

        Subtask newsubtask4 = new Subtask("Subtask #4", "opisanie",TaskStatus.NEW,newepic2.getId());
        Subtask newsubtask5 = new Subtask("Subtask #5", "opisanie",TaskStatus.NEW,newepic2.getId());
        taskManager.createSubtask(newsubtask4);
        taskManager.createSubtask(newsubtask5);

        Subtask newsubtask5_1 = new Subtask("Subtask #5_1", "opisanie 5_1",TaskStatus.NEW,newepic2.getId(),9);
        taskManager.updateSubtask(newsubtask5_1);
        //изменить статус подзадач на DONE, епик меняет статус задач автоматически после того как все подзадачи в статусе DONE
        Subtask newsubtask5_2 = new Subtask("Subtask #5_1", "opisanie 5_1",TaskStatus.DONE,newepic2.getId(),9);
        taskManager.updateSubtask(newsubtask5_2);

        Subtask newsubtask4_1 = new Subtask("Subtask #5_1", "opisanie 5_1",TaskStatus.DONE,newepic2.getId(),8);
        taskManager.updateSubtask(newsubtask4_1);

        newsubtask5_2 = new Subtask("Subtask #5_1", "opisanie 5_1",TaskStatus.NEW,newepic2.getId(),9);
        taskManager.updateSubtask(newsubtask5_2);

        newsubtask4_1 = new Subtask("Subtask #5_1", "opisanie 5_1",TaskStatus.NEW,newepic2.getId(),8);
        taskManager.updateSubtask(newsubtask4_1);






    }
}