package com.demo.todos.screenplay.questions;

import com.demo.todos.screenplay.model.TodoStatus;
import com.demo.todos.screenplay.user_interface.TodoListItem;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.SelectedStatus;
import net.serenitybdd.screenplay.targets.Target;

@Subject("The item status for '#itemName'")
public class TheItemStatus implements Question<TodoStatus> {

    private final String itemName;

    @Override
    public TodoStatus answeredBy(Actor actor) {
        Target completeItemButton = TodoListItem.COMPLETE_ITEM.of(itemName);

        Boolean itemChecked = SelectedStatus.of(completeItemButton).viewedBy(actor).as(Boolean.class);
        return TodoStatus.from(itemChecked);
    }

    public static TheItemStatus forTheItemCalled(String itemName) {
        return new TheItemStatus(itemName);
    }
    public TheItemStatus(String itemName) {
        this.itemName = itemName;
    }
}