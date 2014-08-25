/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import java.io.IOException;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author anandm
 * 
 */
@Controller
@RequestMapping(value = "/notifications")
public class NotificationHandler {

	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public void onRequest(AtmosphereResource resource,
			@PathVariable String username) throws IOException {
		resource.suspend();

		Broadcaster broadcaster = BroadcasterFactory.getDefault().lookup(
				username, true);

		broadcaster.addAtmosphereResource(resource);
	}

}
