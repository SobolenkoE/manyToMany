package phoneBook.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.AbstractBindingResult
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import phoneBook.entity.PhoneGroupEntity
import phoneBook.entity.PhoneRecordEntity
import phoneBook.repo.GroupRepo
import phoneBook.repo.RecordRepo

import phoneBook.servise.GroupService

import java.io.StringWriter
import javax.validation.Valid


@Controller
@RequestMapping("/group")
class GroupController(
    @Autowired
    private val groupService: GroupService,
    private val groupRepo: GroupRepo,
    private val recordRepo: RecordRepo
) {

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int, model: Model):String {
        val groupEntity=groupService.getById(id)
        model.addAttribute("group", groupEntity)
        return "group/group"
    }


    @GetMapping("/all")
    fun getAll(model: Model):String{
        val groupsEntity=groupService.getAllByOrderByName()
        model.addAttribute("groups", groupsEntity)
        return "group/groups"
    }




    @GetMapping("/search/{prefix}")
    fun searchNames(@PathVariable("prefix") searchText:String, model: Model):String{
        val groupsEntity=groupService.findByName(searchText)
        model.addAttribute("groups", groupsEntity)
        return "group/groups"
    }

    @GetMapping("/new")
    fun creategroup(@ModelAttribute ("group") groupEntity: PhoneGroupEntity):String{
        return "group/new"
    }

    @GetMapping("/add")
    fun addRecordToGroup(): String{
        val groupEntity =groupRepo.getById(1)
        val recordEntity =recordRepo.getById(19)
        groupEntity.records?.add(recordEntity)
        groupRepo.save(groupEntity)
        return "успех"
    }


    @PostMapping("/all")
    fun create(@ModelAttribute ("group") @Valid groupEntity: PhoneGroupEntity,
               bindingResult: BindingResult):String {
        println("получили пост запрос")
        if (bindingResult.hasErrors())
            return ("group/new")
        groupRepo.save(groupEntity)
        return "redirect:/group/all"
    }

    @GetMapping("/{id}/edit")
    fun creategroup(@PathVariable id: Int,model:Model):String{
        model.addAttribute("group", groupService.getById(id))
        return "group/edit"
    }


    @PatchMapping("/{id}")
    fun edit(@PathVariable id: Int,
             @ModelAttribute("group") @Valid groupEntity: PhoneGroupEntity,
             bindingResult: BindingResult):String {
        println("получили патч запрос")
        if (bindingResult.hasErrors())
            return "group/edit"

        groupService.update(id,groupEntity)
        return "redirect:/group/${id}"
    }

    @GetMapping("/{id}/delete")
    fun deletegroup(@PathVariable id: Int,model:Model):String{
        model.addAttribute("group", groupService.getById(id))
        return "group/edit"
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int,@ModelAttribute("group") groupEntity: PhoneGroupEntity):String {
        println("получили патч запрос")
        groupService.delete(id)
        return "redirect:/group/all"
    }
//
//    @PutMapping("edit/{id}")
//    fun update(@PathVariable id: Int, @RequestBody phonegroupDto: PhonegroupDto){
//        groupService.update(id,phonegroupDto)
//    }
//
//    @DeleteMapping("delete/{id}")
//    fun delete(@PathVariable id: Int) {
//        groupService.delete(id)
//    }


    @GetMapping("/article")
    fun getArticle(model: Model):String {
        model.addAttribute("name","Евгений")
        return "view"
//    val article = Article()
//    article.setAuthor(getName())
//    article.setContent(getArticleContent())
//    article.setTitle(getTitle())
//    modelAndView.addObject("article", article)
//        modelAndView.setViewName("view")
        }

}


