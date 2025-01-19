package manager;

import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class TaskManager {
    private int id=0;
    private final HashMap<Integer, Task> taskList = new HashMap<>();
    private final HashMap<Integer, Epic> epicList = new HashMap<>();
    private final HashMap<Integer, Subtask> subtaskList = new HashMap<>();

    public void createTask(Task task){

        if(Objects.nonNull(task.getId())){
            return;
        }

        int newId = generateId();
        task.setId(newId);
        taskList.put(task.getId(), task);
    }

    public void updateTask(Task task){
        if(Objects.isNull(task.getId())){
            return;
        }

        if(!taskList.containsKey(task.getId())){
            return;
        }

        taskList.put(task.getId(), task);
    }

    public void createSubtask(Subtask subtask){

        if(Objects.nonNull(subtask.getId())){
            return;
        }

        if(subtask.getEpicId() == 0){
            return;
        }

        if(!epicList.containsKey(subtask.getEpicId())){
            return;
        }


        int newId = generateId();
        subtask.setId(newId);
        subtaskList.put(subtask.getId(), subtask);

        Epic epic = epicList.get(subtask.getEpicId());
        epic.getSubtaskIds().add(newId);

    }

    public void updateSubtask(Subtask subtask){
        if(Objects.isNull(subtask.getEpicId())){
            return;
        }

        if(subtask.getEpicId() == 0){
            return;
        }

        if(!epicList.containsKey(subtask.getEpicId())){
            return;
        }

        subtaskList.put(subtask.getId(), subtask);

        Epic epic = epicList.get(subtask.getEpicId());
        epic.setStatus(TaskStatus.IN_PROGRESS);

        int countDone = 0 ;
        int countNew = 0;

        for(Integer id : epic.getSubtaskIds()){
            Subtask oldSubtask = subtaskList.get(id);
            if((subtask.getStatus().equals(TaskStatus.DONE) && oldSubtask.getStatus().equals(TaskStatus.DONE))) {
                countDone ++;
            }

            if(countDone == epic.getSubtaskIds().size()){
                epic.setStatus(TaskStatus.DONE);
            }

            if((subtask.getStatus().equals(TaskStatus.NEW) && oldSubtask.getStatus().equals(TaskStatus.NEW))) {
                countNew++;
            }

            if(countNew == epic.getSubtaskIds().size()){
                epic.setStatus(TaskStatus.NEW);
            }


        }



    }

    public void createEpic(Epic epic){

        if(Objects.nonNull(epic.getId())){
            return;
        }

        int newId = generateId();
        epic.setId(newId);
        epicList.put(epic.getId(), epic);
    }

    public void updateEpic(Epic epic){
        Epic oldEpic = epicList.get(epic.getId());
        if(epic.getStatus() != oldEpic.getStatus()){
            return ;
        }

        if(!Objects.nonNull(epic.getId())){
            return ;
        }

        if(!epicList.containsKey(epic.getId())){
            epicList.put(epic.getId(), epic);
        }
        epicList.put(epic.getId(), epic);
    }


    // Получение списка всех задач.
    public void printTaskList (){
        for (Task task : taskList.values()){
            System.out.println("Tasklist: "+ task);
        }
    }
    // Получение списка всех задач.
    public void printSubtaskList (){
        for (Subtask subtask : subtaskList.values()){
            System.out.println("Subtasklist: "+ subtask);
        }
    }
// Получение списка всех задач.
    public void printEpicList (){
        for (Epic epic : epicList.values()){
            System.out.println("Epiclist: "+ epic);
        }
    }

    //Получение по идентификатору.
    public void printTask (Integer idTask){
        for (Task task : taskList.values()){
            if(task.getId() == idTask) {
                System.out.println("Taskinfo: " + task);
            }
        }

    }
    //Получение по идентификатору.
    public void printSubtask (Integer idSubtask){
        for (Subtask subtask : subtaskList.values()){
            if(subtask.getId() == idSubtask) {
                System.out.println("subtaskinfo: " + subtask);
            }
        }
    }
//Получение по идентификатору.
    public void printEpic (Integer idEpic){
        for (Epic epic : epicList.values()){
            if(epic.getId() == idEpic) {
                System.out.println("Epicinfo: " + epic);
            }
        }
    }
    //Удаление всех задач.
    public void removeTaskList(){
        taskList.clear();
    }
    //Удаление всех задач.
    public void removeSubtask(){
        subtaskList.clear();
    }
//Удаление всех задач.
    public void removeEpicList(){
        epicList.clear();
    }
    //Удаление по идентификатору.
    public void deleteTask (Integer idTask){
        int removeID=0;
        for (Task task : taskList.values()){
            if(task.getId() == idTask) {
                removeID = idTask;
            }
        }
        taskList.remove(removeID);
    }
    //Удаление по идентификатору.
    public void deleteSubtask (Integer idSubtask){
        int removeID=0;
        for (Subtask subtask : subtaskList.values()){
            if(subtask.getId() == idSubtask) {
                removeID = idSubtask;
            }
        }
        subtaskList.remove(removeID);
    }
    //Удаление по идентификатору.
    public void deleteEpic (Integer idEpic){
        int removeID=0;
        for (Epic epic : epicList.values()){
            if(epic.getId() == idEpic) {
                removeID = idEpic;
            }
        }
        epicList.remove(removeID);
    }

//Получение списка всех подзадач определённого эпика.
    public void printSubtaskListForEpic (Integer idEpic){
        Epic epic = epicList.get(idEpic);

        for (Subtask subtask : subtaskList.values()){
            for (Integer id : epic.getSubtaskIds()){
                if(subtask.getId() == id) {
                    System.out.println("subtaskinfo: " + subtask);
                }
            }

        }

    }


    private int generateId(){
        return ++id;
    }



    public HashMap<Integer, Task> getTaskList() {
        return taskList;
    }

    public HashMap<Integer, Epic> getEpicList() {
        return epicList;
    }

    public HashMap<Integer, Subtask> getSubtaskList() {
        return subtaskList;
    }

    @Override
    public String toString() {
        return "TaskManager{" +
                "id=" + id +
                ", taskList=" + taskList +
                ", epicList=" + epicList +
                ", subtaskList=" + subtaskList +
                '}';
    }
}

