/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author anandm
 *
 */
@Controller
public class NGTemplateController {


	@RequestMapping(value="/partials/{template}")
	public String getTemplate(@PathVariable("template") String template){
		return "partials/"+template;
	}
	
}
