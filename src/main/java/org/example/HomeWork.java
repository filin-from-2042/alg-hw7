package org.example;


import java.util.ArrayList;
import java.util.List;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу
     * <a href="https://acm.timus.ru/problem.aspx?space=1&num=1439">https://acm.timus.ru/problem.aspx?space=1&num=1439</a>
     */
    public List<Integer> getOriginalDoorNumbers(int maxDoors, List<Action> actionList) {
        Treap<Integer> treap = new Treap<>();

        for (int i = 1; i <= maxDoors; i++) {
            treap.add(i);
        }
        List<Integer> result = new ArrayList<>();
        for (Action action : actionList) {
            if (action.isLook) {
                result.add(treap.findKth(action.getDoorNumber()));
            } else {
                treap.removeByDoorNumber(action.getDoorNumber());
            }
        }

        return result;
    }

    /**
     * <h1>Задание 2.</h1>
     * Решить задачу <br/>
     * <a href="https://acm.timus.ru/problem.aspx?space=1&num=1521">https://acm.timus.ru/problem.aspx?space=1&num=1521</a><br/>
     * <h2>Пошагово</h2>
     * Для 5 3 входных данных:<br/>
     * _ -> 3 позиции<br/>
     * _ 1 2 <b>3</b> 4 5 => 3 <br/>
     * <b>1</b> 2 _ 4 5 => 1 <br/>
     * _ 2 4 <b>5</b> => 5 <br/>
     * <b>2</b> 4 _ => 2 <br/>
     * _ <b>4</b> => 4
     */
    public List<Integer> getLeaveOrder(int maxUnits, int leaveInterval) {

        CircleList<Integer> circleList = new CircleList<>();
        for (int i = 1; i <= maxUnits; i++){
            circleList.add(i);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= maxUnits; i++){
            result.add(circleList.remove(leaveInterval));
        }

        return result;
    }

}
