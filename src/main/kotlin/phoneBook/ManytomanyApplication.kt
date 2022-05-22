package phoneBook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory





@SpringBootApplication
class ManytomanyApplication

private var validator: Validator? = null


fun setUp() {
	val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
	@Bean
	validator = factory.validator
}
fun main(args: Array<String>) {



	runApplication<ManytomanyApplication>(*args)
	println("Стартуем")
	val a=4.2F
	println(a)
}

