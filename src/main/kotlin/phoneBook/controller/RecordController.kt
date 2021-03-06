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
import phoneBook.entity.PhoneRecordEntity
import phoneBook.repo.GroupRepo
import phoneBook.repo.RecordRepo
import phoneBook.servise.RecordService
import java.io.StringWriter
import javax.validation.Valid


@Controller
@RequestMapping("/record")
class RecordController(
    @Autowired
    private val recordService: RecordService,
    private val recordRepo: RecordRepo,
    private val groupRepo: GroupRepo
) {

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int, model: Model):String {
        val recordEntity=recordService.getById(id)
        model.addAttribute("record", recordEntity)
        return "record/record"
    }


    @GetMapping("/all")
    fun getAll(model: Model):String{
        val recordsEntity=recordService.getAllByOrderByName()
        model.addAttribute("records", recordsEntity)
        return "record/records"
    }




    @GetMapping("/search/{prefix}")
    fun searchNames(@PathVariable("prefix") searchText:String, model: Model):String{
        val recordsEntity=recordService.findByName(searchText)
        model.addAttribute("records", recordsEntity)
        return "record/records"
    }

    @GetMapping("/new")
    fun createRecord(@ModelAttribute ("record") recordEntity: PhoneRecordEntity,
                     model: Model):String{
        val groups=groupRepo.getAllByOrderByName()
        for (group in groups){
            println(group.name)
        }
        model.addAttribute("groups",groups)
        return "record/new"
    }


    @PostMapping("/all")
    fun create(@ModelAttribute ("record") @Valid recordEntity: PhoneRecordEntity,
               @ModelAttribute ("group") groupId: Int,
               bindingResult: BindingResult):String {
        println("???????????????? ???????? ????????????")
        if (bindingResult.hasErrors())
            return ("record/new")
        recordEntity.group?.add(groupRepo.getById(groupId))
        recordRepo.save(recordEntity)
        return "redirect:/record/all"
    }

    @GetMapping("/{id}/edit")
    fun createRecord(@PathVariable id: Int,model:Model):String{
        model.addAttribute("record", recordService.getById(id))
        model.addAttribute("groups", groupRepo.getAllByOrderByName())
        return "record/edit"
    }


    @PatchMapping("/{id}")
    fun edit(@PathVariable id: Int,
             @ModelAttribute("record") @Valid recordEntity: PhoneRecordEntity,
             @ModelAttribute("group") idGroup: Int,
             bindingResult: BindingResult):String {
        if (bindingResult.hasErrors())
            return "record/edit"
        recordEntity.group?.add(groupRepo.getById(idGroup))
        recordService.update(id,recordEntity)
        return "redirect:/record/${id}"
    }

    @GetMapping("/{id}/delete")
    fun deleteRecord(@PathVariable id: Int,model:Model):String{
        model.addAttribute("record", recordService.getById(id))
        return "record/edit"
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int,@ModelAttribute("record") recordEntity: PhoneRecordEntity):String {
        println("???????????????? ???????? ????????????")
        recordService.delete(id)
        return "redirect:/record/all"
    }
//
//    @PutMapping("edit/{id}")
//    fun update(@PathVariable id: Int, @RequestBody phoneRecordDto: PhoneRecordDto){
//        recordService.update(id,phoneRecordDto)
//    }
//
//    @DeleteMapping("delete/{id}")
//    fun delete(@PathVariable id: Int) {
//        recordService.delete(id)
//    }


    @GetMapping("/article")
    fun getArticle(model: Model):String {
        model.addAttribute("name","??????????????")
        return "view"
//    val article = Article()
//    article.setAuthor(getName())
//    article.setContent(getArticleContent())
//    article.setTitle(getTitle())
//    modelAndView.addObject("article", article)
//        modelAndView.setViewName("view")
        }

}


