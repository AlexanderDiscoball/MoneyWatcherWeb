package alex.disco.ball.entity;

import java.util.HashMap;
import java.util.Map;

public enum Category {
    ALL("Все категории"),
    TRANSPORT("Транспорт"),
    FOOD("Питание"),
    CLOTHES("Одежда"),
    FUN("Развлечения"),
    RENT("Аренда"),
    DEBT("Долги"),
    SPORT("Спорт"),
    PERSONALCARE("Уход за собой"),
    VACATION("Отпуск"),
    SAVINGS("Сбережения"),
    ELSE("Другое");

    private static Map<String,String> rus = new HashMap<>();
    static {
        for (Category category : Category.values()) {
            rus.put(category.getTitle(), category.getName());
        }
    }

    private String title;

    Category(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getName(){
        return super.name();
    }

    @Override
    public String toString() {
        return title;
    }

    public static Category getCategory(String name) {
        return Category.valueOf(rus.get(name));
    }

    public static Category next(int id){
        while(id > values().length){
            id-=values().length;
        }
        return values()[id];
    }
}
