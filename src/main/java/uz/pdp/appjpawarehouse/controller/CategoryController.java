package uz.pdp.appjpawarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpawarehouse.entity.Category;
import uz.pdp.appjpawarehouse.payload.CategoryDTO;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/upload")
    public Result addCategory(@RequestBody CategoryDTO categoryDTO){

        Result result = categoryService.addCategory(categoryDTO);
        return result;
    }

    @GetMapping(value = "/get")
    public List<Category> getCategories(){
        List<Category> categoryList = categoryService.getCategories();
        return categoryList;
    }

    @GetMapping(value = "/subCategory/{parentCategoryId}")
    public Result getSubCategory(@PathVariable Integer parentCategoryId){

        Result subCategory = categoryService.getSubCategory(parentCategoryId);
        return subCategory;
    }

    @PutMapping(value = "/{categoryID}")
    public Result editCategory(@PathVariable Integer categoryID, @RequestBody CategoryDTO categoryDTO){

        Result result = categoryService.editCategory(categoryID, categoryDTO);
        return result;
    }

    @DeleteMapping(value = "/{categoryID}")
    public Result deleteCategory(@PathVariable Integer categoryID){

        Result result = categoryService.deleteCategory(categoryID);
        return result;
    }
}
