package uz.pdp.appjpawarehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.appjpawarehouse.entity.Category;
import uz.pdp.appjpawarehouse.payload.CategoryDTO;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.repositort.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Result addCategory(CategoryDTO categoryDTO) {

        Category category = new Category();
        category.setName(categoryDTO.getName());

        if (categoryDTO.getParenCategoryId() != 0) {

            Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getParenCategoryId());

            if (!optionalCategory.isPresent()) {
                return new Result("There is not parent category", false);
            }
            category.setParentCategoryId(optionalCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Category successfully saved", true);
    }

    public List<Category> getCategories() {

        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    public Result getSubCategory(@PathVariable Integer parentCategoryId) {

        Optional<Category> parentCategory = categoryRepository.findById(parentCategoryId);
        if (parentCategory.isPresent()) {

            List<Category> subCategoryList = categoryRepository.findAllByParentCategoryId(parentCategory.get());
            return new Result("Subcategories", true, subCategoryList);
        }
        return new Result("There is not Subcategories yet.", false);
    }

    public Result editCategory(Integer categoryID, CategoryDTO categoryDTO) {

        Optional<Category> optionalCategory = categoryRepository.findById(categoryID);
        if (!optionalCategory.isPresent())
            return new Result("Invalid category ID", false);

        Category editedCategory = optionalCategory.get();
        editedCategory.setName(categoryDTO.getName());

        if (categoryDTO.getParenCategoryId() != 0) {
            optionalCategory = categoryRepository.findById(categoryDTO.getParenCategoryId());

            if (!optionalCategory.isPresent()) {
                return new Result("There is not parent category", false);
            }
            editedCategory.setParentCategoryId(optionalCategory.get());
        }
        categoryRepository.save(editedCategory);
        return new Result("Category successfully edited", true);
    }

    public Result deleteCategory(Integer categoryID){

        Optional<Category> optionalCategory = categoryRepository.findById(categoryID);
        if (!optionalCategory.isPresent())
            return new Result("Invalid Category ID", false);

        categoryRepository.deleteById(categoryID);
        return new Result("Category deleted", true);
    }
}
