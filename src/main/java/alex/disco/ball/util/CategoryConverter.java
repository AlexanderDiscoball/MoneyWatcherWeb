package alex.disco.ball.util;

import alex.disco.ball.entity.Category;

import javax.persistence.AttributeConverter;

public class CategoryConverter implements AttributeConverter<Category, String> {
    @Override
    public String convertToDatabaseColumn(Category attribute) {
        return attribute.getTitle();
    }

    @Override
    public Category convertToEntityAttribute(String dbData) {
        return Category.getCategory(dbData);
    }
}
